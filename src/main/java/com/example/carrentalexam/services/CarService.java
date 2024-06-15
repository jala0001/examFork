package com.example.carrentalexam.services;

import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public void createNewCar(String frameNumber, String brand, String model, int monthlyPrice, String registrationNumber, String status) {
        carRepository.createNewCar(frameNumber, brand, model, monthlyPrice, registrationNumber, status);
    }


    public Car getAllCarsReturned(int rentalContractCarId) {
        return carRepository.getAllCarsReturned(rentalContractCarId);
    }

    public Car getCarRented(int carId) {
        return carRepository.getCarRented(carId);
    }

    public void changeCarToAvailable(int carId) {
        carRepository.changeCarToAvailable(carId);
    }

    public void changeCarToMaintenance(int carId) {
        carRepository.changeCarToMaintenance(carId);
    }

    public Car getCarMaintenance(int carId) {
        return carRepository.getCarMaintenance(carId);
    }

    public List<Car> getAllCarsThatAreAvailable() {
        return carRepository.getAllCarsThatAreAvailabe();
    }


    public double getMonthlyPriceForCar(int carId) {
        return carRepository.getMonthlyPriceForCar(carId);
    }

    public void updateCar(String frameNumber, String brand, String model, double monthlyPrice, String registrationNumber, String status, int carId) {
        carRepository.updateCar(frameNumber, brand, model, monthlyPrice, registrationNumber, status, carId);
    }

    public Car getCar(int carId) {
        return carRepository.getCar(carId);
    }

    public void deleteCar(int carId) {
        carRepository.deleteCar(carId);
    }

    public List<Car> getAllCarsThatAreMaintenance() {
        return carRepository.getAllCarsThatAreMaintenance();
    }
}
