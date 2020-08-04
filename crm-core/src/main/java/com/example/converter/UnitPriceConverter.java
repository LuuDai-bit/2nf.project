package com.example.converter;

import com.example.dto.UnitPriceDTO;
import com.example.entity.UnitPriceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitPriceConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UnitPriceDTO convertToDto(UnitPriceEntity entity){
        UnitPriceDTO result = modelMapper.map(entity, UnitPriceDTO.class);
        return result;
    }

    public UnitPriceEntity convertToEntity(UnitPriceDTO dto){
        UnitPriceEntity result = modelMapper.map(dto, UnitPriceEntity.class);
        return result;
    }
}
