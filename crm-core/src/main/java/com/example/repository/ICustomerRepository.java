package com.example.repository;

import com.example.entity.CustomerEntity;
import com.example.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository  extends JpaRepository<CustomerEntity, Long>, CustomerRepositoryCustom {
}
