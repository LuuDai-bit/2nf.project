package com.example.converter;

import com.example.dto.PaymentDTO;
import com.example.entity.PaymentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PaymentDTO convertToDto(PaymentEntity entity){
        PaymentDTO result = modelMapper.map(entity, PaymentDTO.class);
        return result;
    }

    public PaymentEntity convertToEntity(PaymentDTO dto){
        PaymentEntity result = modelMapper.map(dto, PaymentEntity.class);
        return result;
    }
}
