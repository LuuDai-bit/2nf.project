package com.example.repository.custom;

import com.example.dto.UnitPriceDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface UnitPriceRepositoryCustom {
    List<?> findAll(UnitPriceDTO unitPriceDTO, Pageable pageable);
    Long getTotalItems(UnitPriceDTO unitPriceDTO);
}
