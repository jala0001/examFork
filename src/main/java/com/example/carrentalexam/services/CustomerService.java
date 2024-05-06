package com.example.carrentalexam.services;

import com.example.carrentalexam.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public void createNewCustomer(String name, String address, String number, String email) {
        customerRepository.createNewCustomer(name, address, number, email);
    }
}
