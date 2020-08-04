package com.example.repository.custom;

import com.example.dto.PaymentDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface PaymentRepositoryCustom {
    List<?> findAll(PaymentDTO paymentDTO, Pageable pageable);
    Long getTotalItems(PaymentDTO paymentDTO);
}
