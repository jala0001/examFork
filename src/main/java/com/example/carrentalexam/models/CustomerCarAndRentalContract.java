package com.example.carrentalexam.models;

import java.time.LocalDate;

public class CustomerCarAndRentalContract {
    private String name;
    private String brand;
    private LocalDate startDate;
    private LocalDate endDate;
    private int startsInXDays;
    private int endsInXDays;

    public CustomerCarAndRentalContract() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public int getStartsInXDays() {
        return startsInXDays;
    }

    public void setStartsInXDays(int startsInXDays) {
        this.startsInXDays = startsInXDays;
    }

    public int getEndsInXDays() {
        return endsInXDays;
    }

    public void setEndsInXDays(int endsInXDays) {
        this.endsInXDays = endsInXDays;
    }
}
