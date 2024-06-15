package com.example.carrentalexam.repositories;

import com.example.carrentalexam.models.*;
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

    public void createRentalContract(int customerId, int carId, LocalDate startDate, LocalDate endDate, double price, int locationId, String conditionOnDelivery, String conditionUponReturn) {
        String query = "insert into rental_contracts(customer_id, car_id, start_date, end_date, " +
                "price, location_id, condition_on_delivery, condition_upon_return, is_rental_contract_ended)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, customerId, carId, startDate, endDate, price, locationId, conditionOnDelivery, conditionUponReturn, "no"); // "no" er hardcodet til at kontrakten ikke er afsluttet.
    }


    public List<Car> getRentedCarsCount() {
        String query = "SELECT * FROM cars where status = 'RENTED'";
        RowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public double getTotalRevenue() {
        String query = "SELECT SUM(price) FROM rental_contracts WHERE start_date <= CURDATE() AND end_date >= CURDATE()";
        Double result = jdbcTemplate.queryForObject(query, Double.class);
        return result != null ? result : 0.0;
    }



    public List<RentalContract> getAllRentalContractWhereTheCarHasBeenReturned() {
        String query = "SELECT * FROM rental_contracts WHERE end_date < CURDATE() AND is_rental_contract_ended = 'no';";
        RowMapper<RentalContract> rowMapper = new BeanPropertyRowMapper<>(RentalContract.class);
        return jdbcTemplate.query(query, rowMapper);
    }


    public List<RentalContract> getAllRentalContractsThatsActive() {
        String query = "select * from rental_contracts where is_rental_contract_ended = 'no';";
        RowMapper rowMapper = new BeanPropertyRowMapper(RentalContract.class);
        return jdbcTemplate.query(query, rowMapper);

    }

    public void concludeContract(int rentalContractId) {
        String query = "update rental_contracts set is_rental_contract_ended = 'yes' where rental_contract_id = ?;";
        jdbcTemplate.update(query, rentalContractId);
    }

    public RentalContract getRentalContract(int rentalContractId) {
        String query = "Select * from rental_contracts where rental_contract_id = ?;";
        RowMapper<RentalContract> rowMapper = new BeanPropertyRowMapper<>(RentalContract.class);
        return jdbcTemplate.queryForObject(query, rowMapper, rentalContractId);
    }

    public void changeConditionUponReturn(int rentalContractId) {
        String query = "update rental_contracts set condition_upon_return = 'same condition as before' where rental_contract_id = ?;";
        jdbcTemplate.update(query, rentalContractId);
    }

    public void changeConditionUponReturnToDamaged(int rentalContractId) {
        String query = "update rental_contracts set condition_upon_return = 'had one or more damages' where rental_contract_id = ?;";
        jdbcTemplate.update(query, rentalContractId);
    }

    public List<RentalContract> getAllRentalContracts() {
        String query = "SELECT * FROM rental_contracts";
        RowMapper<RentalContract> rowMapper = new BeanPropertyRowMapper<>(RentalContract.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public void deleteRentalContract(int rentalContractId) {
        String query = "delete from rental_contracts where rental_contract_id = ?;";
        jdbcTemplate.update(query, rentalContractId);
    }

    public List<Location> getAllLocations() {
        String query = "select * from locations;";
        RowMapper<Location> rowMapper = new BeanPropertyRowMapper<>(Location.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public List<CustomerCarAndRentalContract> getAllCustomerCarAndRentalContracts() {
        String query = "SELECT \n" +
                "    c.name, \n" +
                "    ca.brand, \n" +
                "    rc.start_date, \n" +
                "    rc.end_date, \n" +
                "    DATEDIFF(rc.start_date, CURDATE()) AS starts_in_x_days, \n" +
                "    DATEDIFF(rc.end_date, CURDATE()) AS ends_in_x_days\n" +
                "FROM \n" +
                "    customers c\n" +
                "JOIN \n" +
                "    rental_contracts rc ON c.customer_id = rc.customer_id\n" +
                "JOIN \n" +
                "    cars ca ON rc.car_id = ca.car_id;\n;";
        RowMapper<CustomerCarAndRentalContract> rowMapper = new BeanPropertyRowMapper<>(CustomerCarAndRentalContract.class);
        return jdbcTemplate.query(query, rowMapper);
    }
}
