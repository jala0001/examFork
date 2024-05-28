package com.example.carrentalexam.models;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RentalContractTest {

    private RentalContract rentalContract;

    @BeforeEach
    void setUp() {
        rentalContract = new RentalContract();
        rentalContract.setRentalContractId(1);
        rentalContract.setCustomerId(101);
        rentalContract.setCarId(201);
        rentalContract.setStartDate(LocalDate.of(2023, 5, 1));
        rentalContract.setEndDate(LocalDate.of(2023, 6, 1));
        rentalContract.setPrice(1000.00);
        rentalContract.setPickUpLocation("Downtown");
        rentalContract.setConditionOnDelivery("Good");
        rentalContract.setConditionUponReturn("Fair");
        rentalContract.setIsRentalContractEnded("No");
    }

    @Test
    void testGetRentalContractId() {
        assertEquals(1, rentalContract.getRentalContractId(), "RentalContract ID should be 1");
    }

    @Test
    void testGetCustomerId() {
        assertEquals(101, rentalContract.getCustomerId(), "Customer ID should be 101");
    }

    @Test
    void testGetCarId() {
        assertEquals(201, rentalContract.getCarId(), "Car ID should be 201");
    }

    @Test
    void testGetStartDate() {
        assertEquals(LocalDate.of(2023, 5, 1), rentalContract.getStartDate(), "Start date should be 2023-05-01");
    }

    @Test
    void testGetEndDate() {
        assertEquals(LocalDate.of(2023, 6, 1), rentalContract.getEndDate(), "End date should be 2023-06-01");
    }

    @Test
    void testGetPrice() {
        assertEquals(1000.00, rentalContract.getPrice(), 0.01, "Price should be 1000.00");
    }

    @Test
    void testGetPickUpLocation() {
        assertEquals("Downtown", rentalContract.getPickUpLocation(), "Pick up location should be 'Downtown'");
    }

    @Test
    void testGetConditionOnDelivery() {
        assertEquals("Good", rentalContract.getConditionOnDelivery(), "Condition on delivery should be 'Good'");
    }

    @Test
    void testGetConditionUponReturn() {
        assertEquals("Fair", rentalContract.getConditionUponReturn(), "Condition upon return should be 'Fair'");
    }

    @Test
    void testIsRentalContractEnded() {
        assertEquals("No", rentalContract.getIsRentalContractEnded(), "Rental contract should not be ended");
    }
}
