package com.example.carrentalexam.services;

import com.example.carrentalexam.models.Customer;
import com.example.carrentalexam.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public void createNewCustomer(String name, String address, String number, String email) {
        customerRepository.createNewCustomer(name, address, number, email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }


    public void updateCustomer(String name, String address, String number, String email, int customerId) {
        customerRepository.updateCustomer(name, address, number, email, customerId);
    }


    public void deleteCustomer(int customerId) {
        customerRepository.deleteCustomer(customerId);
    }

    public Customer getCustomer(int customerId) {
        return customerRepository.getCustomer(customerId);

    }
}
