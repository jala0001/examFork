package com.example.carrentalexam.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createNewCustomer(String name, String address, String number, String email) {
    String query = "insert into customers(name, address, number, email)" +
            "values(?, ?, ?, ?);";
    jdbcTemplate.update(query, name, address, number, email);
    }
}
