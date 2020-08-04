package com.example.service;

import com.example.dto.UnitPriceDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface IUnitPriceService {
    UnitPriceDTO getOneUnitPriceById(Long id);
    List<UnitPriceDTO> searchUnitPrices(UnitPriceDTO modelSearch, Pageable pageable);
    int getTotalItems(UnitPriceDTO modelSearch);
    void saveUnitPrice(UnitPriceDTO unitPrice);
    void deleteUnitPrices(List<Long> unitPrices);
    List<UnitPriceDTO> getAllUnitPrices();
}
