package com.example.repository.custom;

import com.example.dto.CustomerDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<?> findAll(CustomerDTO customerDTO, Pageable pageable);
    List<?> findAllWithoutPageable(CustomerDTO customerDTO);
    Long getTotalItems(CustomerDTO customerDTO);
}
