package com.example.carrentalexam.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class RentalContractRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createRentalContract(int customerId, int carId, LocalDate startDate, LocalDate endDate, double price, String pickUpLocation, String conditionOnDelivery, String conditionUponReturn) {
        String query = "insert into rental_contracts(customer_id, car_id, start_date, end_date, " +
                "price, pick_up_location, condition_on_delivery, condition_upon_return)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, customerId, carId, startDate, endDate, price, pickUpLocation, conditionOnDelivery, conditionUponReturn);
    }
}
