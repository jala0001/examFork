package com.example.carrentalexam.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(100);
        customer.setName("John Doe");
        customer.setAddress("123 Elm Street");
        customer.setNumber("1234567890");
        customer.setEmail("johndoe@example.com");
    }

    @Test
    void testGetCustomerId() {
        assertEquals(100, customer.getCustomerId(), "Customer ID should be 100");
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", customer.getName(), "Name should be John Doe");
    }

    @Test
    void testGetAddress() {
        assertEquals("123 Elm Street", customer.getAddress(), "Address should be 123 Elm Street");
    }

    @Test
    void testGetNumber() {
        assertEquals("1234567890", customer.getNumber(), "Number should be 1234567890");
    }

    @Test
    void testGetEmail() {
        assertEquals("johndoe@example.com", customer.getEmail(), "Email should be johndoe@example.com");
    }

    @Test
    void testSetCustomerId() {
        customer.setCustomerId(101);
        assertEquals(101, customer.getCustomerId(), "Customer ID should be updated to 101");
    }


}
