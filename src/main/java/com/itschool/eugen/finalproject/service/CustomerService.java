package com.itschool.eugen.finalproject.service;

import com.itschool.eugen.finalproject.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerDTO addCustomer(CustomerDTO customerDTO);

    CustomerDTO findCustomer(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    List<CustomerDTO> findAllCustomers();

    boolean deleteCustomer(Long id);

}
