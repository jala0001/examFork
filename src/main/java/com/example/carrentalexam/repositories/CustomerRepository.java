package com.example.carrentalexam.repositories;

import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.models.Customer;
import com.example.carrentalexam.models.EmployeeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public void createNewCustomer(String name, String address, String number, String email) {
    String query = "insert into customers(name, address, number, email)" +
            "values(?, ?, ?, ?);";
    jdbcTemplate.update(query, name, address, number, email);
    }

    public List<Customer> getAllCustomers() {
        String query = "SELECT * FROM customers;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Customer.class);
        return jdbcTemplate.query(query, rowMapper);
    }


    public void updateCustomer(String name, String address, String number, String email, int customerId) {
        String query = "update customers set name = ?, address = ?, number = ?, email = ? where customer_id = ?;";
        jdbcTemplate.update(query, name, address, number, email, customerId);
    }


    public void deleteCustomer(int customerId) {
        String query = "delete from customers where customer_id = ?;";
        jdbcTemplate.update(query, customerId);
    }

    public Customer getCustomer(int customerId) {
        String query = "select * from customers where customer_id = ?;";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.queryForObject(query, rowMapper, customerId);
    }
}
