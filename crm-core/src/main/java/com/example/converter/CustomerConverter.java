package com.example.converter;

import com.example.dto.CustomerDTO;
import com.example.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertToDto(CustomerEntity entity){
        CustomerDTO result = modelMapper.map(entity, CustomerDTO.class);
        return result;
    }

    public CustomerEntity convertToEntity(CustomerDTO dto){
        CustomerEntity result = modelMapper.map(dto, CustomerEntity.class);
        return result;
    }
}
