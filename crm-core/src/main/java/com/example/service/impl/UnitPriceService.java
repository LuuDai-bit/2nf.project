package com.example.service.impl;

import com.example.converter.UnitPriceConverter;
import com.example.dto.UnitPriceDTO;
import com.example.entity.UnitPriceEntity;
import com.example.paging.Pageable;
import com.example.repository.IUnitPriceRepository;
import com.example.service.IUnitPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitPriceService implements IUnitPriceService {
    @Autowired
    private IUnitPriceRepository unitPriceRepository;

    @Autowired
    private UnitPriceConverter unitPriceConverter;

    @Override
    public UnitPriceDTO getOneUnitPriceById(Long id) {
        UnitPriceEntity unitPriceEntity = unitPriceRepository.findOne(id);
        UnitPriceDTO UnitPriceDTO = unitPriceConverter.convertToDto(unitPriceEntity);
        return UnitPriceDTO;
    }

    @Override
    public List<UnitPriceDTO> searchUnitPrices(UnitPriceDTO modelSearch, Pageable pageable) {

        List<?> unitPriceEntities = new ArrayList<>();
        List<UnitPriceDTO> result = new ArrayList<>();

        unitPriceEntities = unitPriceRepository.findAll(modelSearch, pageable);
        for (Object item : unitPriceEntities) {
            UnitPriceEntity unitPriceEntity = new UnitPriceEntity();
            try {
                unitPriceEntity = (UnitPriceEntity) item;
            } catch (Exception e) {
                unitPriceEntity = (UnitPriceEntity) ((Object[]) item)[0];
            }
            UnitPriceDTO unitPriceDTO = unitPriceConverter.convertToDto(unitPriceEntity);

            result.add(unitPriceDTO);
        }

        return result;
    }

    @Override
    public int getTotalItems(UnitPriceDTO modelSearch) {
        int totalItem = 0;
        totalItem = unitPriceRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    @Override
    public void saveUnitPrice(UnitPriceDTO unitPriceDto) {
        if(unitPriceDto.getId() !=null && unitPriceRepository.findOne(unitPriceDto.getId()) != null){
            UnitPriceEntity unitPriceEntity = unitPriceRepository.findOne(unitPriceDto.getId());
            unitPriceEntity.setRoomPrice(unitPriceDto.getRoomPrice());
            unitPriceEntity.setElectricityPrice(unitPriceDto.getElectricityPrice());
            unitPriceEntity.setWaterPrice(unitPriceDto.getWaterPrice());
            unitPriceEntity.setEffectiveDate(unitPriceDto.getEffectiveDate());
            unitPriceRepository.save(unitPriceEntity);
        }
        else {
            UnitPriceEntity unitPriceEntity = unitPriceConverter.convertToEntity(unitPriceDto);
            unitPriceRepository.save(unitPriceEntity);
        }
    }

    @Override
    public void deleteUnitPrices(List<Long> unitPrices) {
        for(Long unitPriceId : unitPrices){
            unitPriceRepository.delete(unitPriceId);
        }
    }

    @Override
    public List<UnitPriceDTO> getAllUnitPrices() {
        List<UnitPriceEntity> unitPrices = unitPriceRepository.findAll();
        List<UnitPriceDTO> unitPriceDTOS = new ArrayList<UnitPriceDTO>();
        for(UnitPriceEntity unitPrice : unitPrices){
            UnitPriceDTO unitPriceDTO = unitPriceConverter.convertToDto(unitPrice);
            unitPriceDTOS.add(unitPriceDTO);
        }
        return unitPriceDTOS;
    }
}
