package com.example.carrentalexam.models;

import java.time.LocalDate;
import java.util.Date;

public class RentalContract {
    private int rentalContractId;
    private int customerId;
    private int carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private String pickUpLocation;
    private String conditionOnDelivery;
    private String conditionUponReturn;
    private String isRentalContractEnded;

    public RentalContract() {
    }

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

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
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
