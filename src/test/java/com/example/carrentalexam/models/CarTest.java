package com.example.carrentalexam.models;

import com.example.carrentalexam.enums.CarStatus;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId(1);
        car.setFrameNumber("XYZ123");
        car.setBrand("Toyota");
        car.setModel("Corolla");
        car.setMonthlyPrice(299.99);
        car.setRegistrationNumber("AB12345");
        car.setStatus(CarStatus.AVAILABLE);
    }

    @Test
    void testGetCarId() {
        assertEquals(1, car.getCarId(), "Car ID should be 1");
    }

    @Test
    void testGetFrameNumber() {
        assertEquals("XYZ123", car.getFrameNumber(), "Frame number should match");
    }

    @Test
    void testGetBrand() {
        assertEquals("Toyota", car.getBrand(), "Brand should be Toyota");
    }

    @Test
    void testGetModel() {
        assertEquals("Corolla", car.getModel(), "Model should be Corolla");
    }

    @Test
    void testGetMonthlyPrice() {
        assertEquals(299.99, car.getMonthlyPrice(), 0.001, "Monthly price should be 299.99");
    }

    @Test
    void testGetRegistrationNumber() {
        assertEquals("AB12345", car.getRegistrationNumber(), "Registration number should match");
    }

    @Test
    void testGetStatus() {
        assertEquals(CarStatus.AVAILABLE, car.getStatus(), "Status should be AVAILABLE");
    }

    @Test
    void testSetCarId() {
        car.setCarId(2);
        assertEquals(2, car.getCarId(), "Car ID should be updated to 2");
    }


}
