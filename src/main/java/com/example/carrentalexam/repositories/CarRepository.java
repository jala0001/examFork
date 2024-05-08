package com.example.carrentalexam.repositories;

import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.models.EmployeeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Car> getAllCars() {
        String query = "SELECT * FROM cars;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Car.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public void createNewCar(String frameNumber, String brand, String model, String registrationNumber, String status) {
        String query = "insert into cars(frame_number, brand, model, registration_number, status)" +
                "values(?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, frameNumber, brand, model, registrationNumber, status);
    }

    public void updateCarStatus(int carId, String status) { // NY
        String query = "UPDATE cars SET status = ? WHERE car_id = ?";
        jdbcTemplate.update(query, status, carId);
    }
}
