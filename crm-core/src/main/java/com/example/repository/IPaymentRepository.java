package com.example.repository;

import com.example.entity.PaymentEntity;
import com.example.repository.custom.PaymentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<PaymentEntity, Long>, PaymentRepositoryCustom {
}
