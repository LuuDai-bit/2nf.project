package com.example.service;

import com.example.dto.PaymentDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface IPaymentService {
    PaymentDTO getOnePaymentById(Long id);
    List<PaymentDTO> searchPayments(PaymentDTO modelSearch, Pageable pageable);
    int getTotalItems(PaymentDTO modelSearch);
    void savePayment(PaymentDTO Payment);
    void deletePayments(List<Long> Payments);
    List<PaymentDTO> getAllPayments();
}
