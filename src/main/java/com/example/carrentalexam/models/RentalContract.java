package com.example.carrentalexam.models;

import java.time.LocalDate;

public class RentalContract {
    private int rentalContractId;
    private int customerId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private int locationId;  // Ændret fra String pickUpLocation til int locationId
    private String conditionOnDelivery;
    private String conditionUponReturn;
    private String isRentalContractEnded;

    public RentalContract() {
    }

    // Getters and setters for all fields
    public int getRentalContractId() {
        return rentalContractId;
    }

    public void setRentalContractId(int rentalContractId) {
        this.rentalContractId = rentalContractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getConditionOnDelivery() {
        return conditionOnDelivery;
    }

    public void setConditionOnDelivery(String conditionOnDelivery) {
        this.conditionOnDelivery = conditionOnDelivery;
    }

    public String getConditionUponReturn() {
        return conditionUponReturn;
    }

    public void setConditionUponReturn(String conditionUponReturn) {
        this.conditionUponReturn = conditionUponReturn;
    }

    public String getIsRentalContractEnded() {
        return isRentalContractEnded;
    }

    public void setIsRentalContractEnded(String isRentalContractEnded) {
        this.isRentalContractEnded = isRentalContractEnded;
    }
}
