package com.example.carrentalexam.repositories;

import com.example.carrentalexam.enums.CarStatus;
import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.models.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentalContractRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public RentalContractRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createRentalContract(int customerId, int carId, LocalDate startDate, LocalDate endDate, double price, String pickUpLocation, String conditionOnDelivery, String conditionUponReturn) {
        String query = "insert into rental_contracts(customer_id, car_id, start_date, end_date, " +
                "price, pick_up_location, condition_on_delivery, condition_upon_return)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, customerId, carId, startDate, endDate, price, pickUpLocation, conditionOnDelivery, conditionUponReturn);
    }


    public List<RentalContract> getRentedCarsCount() { // NY
        String query = "SELECT * FROM cars where status = 'RENTED'";
        RowMapper rowMapper = new BeanPropertyRowMapper(RentalContract.class);
        return jdbcTemplate.query(query, rowMapper);
    }


    public double getTotalRevenue() { // NY
        String query = "SELECT SUM(price) FROM rental_contracts";
        return jdbcTemplate.queryForObject(query, Double.class);
    }

    public List<RentalContract> getAllRentalContractWhereTheCarHasBeenReturned() {
        String query = "SELECT * FROM rental_contracts where end_date < curdate();";
        RowMapper rowMapper = new BeanPropertyRowMapper(RentalContract.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public List<RentalContract> getAllRentalContracts() {
        String query = "select * from rental_contracts;";
        RowMapper rowMapper = new BeanPropertyRowMapper(RentalContract.class);
        return jdbcTemplate.query(query, rowMapper);

    }
}
