package com.example.service.impl;

import com.example.converter.CustomerConverter;
import com.example.dto.CustomerDTO;
import com.example.entity.CustomerEntity;
import com.example.paging.Pageable;
import com.example.repository.ICustomerRepository;
import com.example.repository.IUserRepository;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public CustomerDTO getOneCustomerById(Long id) {

        CustomerEntity customerEntity = customerRepository.findOne(id);
        CustomerDTO customerDTO = customerConverter.convertToDto(customerEntity);
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> searchCustomers(CustomerDTO modelSearch, Pageable pageable) {

        List<?> customerEntities = new ArrayList<>();
        List<CustomerDTO> result = new ArrayList<>();

        customerEntities = customerRepository.findAll(modelSearch, pageable);
        for (Object item : customerEntities) {
            CustomerEntity customerEntity = new CustomerEntity();
            try {
                customerEntity = (CustomerEntity) item;
            } catch (Exception e) {
                customerEntity = (CustomerEntity) ((Object[]) item)[0];
            }
            CustomerDTO customerDTO = customerConverter.convertToDto(customerEntity);

            result.add(customerDTO);
        }

        return result;
    }

    @Override
    public int getTotalItems(CustomerDTO modelSearch) {
        int totalItem = 0;
        totalItem = customerRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    @Override
    public void saveCustomer(CustomerDTO customerDto) {
        if(customerDto.getId() !=null && customerRepository.findOne(customerDto.getId()) != null){
            CustomerEntity customerEntity = customerRepository.findOne(customerDto.getId());

            customerRepository.save(customerEntity);
        }
        else {
            if(customerDto.getUser_id() == null || userRepository.findOne(customerDto.getUser_id())==null){
                return;
            }
            CustomerEntity customerEntity = customerConverter.convertToEntity(customerDto);
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public void deleteCustomers(List<Long> customers) {
        for(Long customerId : customers){
            customerRepository.delete(customerId);
        }
    }
}
