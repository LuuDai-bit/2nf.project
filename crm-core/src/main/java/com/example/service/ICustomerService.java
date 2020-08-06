package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.paging.Pageable;

import java.util.List;

public interface ICustomerService {
    CustomerDTO getOneCustomerById(Long id);
    List<CustomerDTO> searchCustomers(CustomerDTO modelSearch, Pageable pageable);
    int getTotalItems(CustomerDTO modelSearch);
    void saveCustomer(CustomerDTO Customer);
    void deleteCustomers(List<Long> Customers);
    List<CustomerDTO> getAllCustomers();
}
