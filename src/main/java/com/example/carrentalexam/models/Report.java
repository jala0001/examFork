package com.example.carrentalexam.models;

import java.time.LocalDate;

public class Report {
    private int reportId;
    private LocalDate date;
    private int numberOfRentedCars;
    private double totalRevenue;

    public Report() {
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getNumberOfRentedCars() {
        return numberOfRentedCars;
    }

    public void setNumberOfRentedCars(int numberOfRentedCars) {
        this.numberOfRentedCars = numberOfRentedCars;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
