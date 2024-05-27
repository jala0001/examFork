package com.example.carrentalexam.services;

import com.example.carrentalexam.enums.CarStatus;
import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.models.RentalContract;
import com.example.carrentalexam.repositories.CarRepository;
import com.example.carrentalexam.repositories.RentalContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalContractService {
    @Autowired
    private RentalContractRepository rentalContractRepository;
    @Autowired
    private CarRepository carRepository;

    public RentalContractService(RentalContractRepository rentalContractRepository) {
        this.rentalContractRepository = rentalContractRepository;
    }

    public void createRentalContract(int customerId, int carId, LocalDate startDate,
                                     LocalDate endDate, double price, String pickUpLocation,
                                     String conditionOnDelivery, String conditionUponReturn) {

        rentalContractRepository.createRentalContract(customerId, carId, startDate, endDate, price,
                pickUpLocation, conditionOnDelivery, conditionUponReturn);

        carRepository.updateCarStatus(carId, CarStatus.RENTED.name());
    }


    public List<Car> getRentedCarsCount() { // NY - ændret at den modtager CAR objekter og ikke RentalContract objekter
        return rentalContractRepository.getRentedCarsCount();
    }

    public double getTotalRevenue() { // NY
        return rentalContractRepository.getTotalRevenue();
    }

    public List<RentalContract> getAllRentalContractWhereTheCarHasBeenReturned() {
        return rentalContractRepository.getAllRentalContractWhereTheCarHasBeenReturned();
    }

    public List<RentalContract> getAllRentalContractsThatsActive() {
        return rentalContractRepository.getAllRentalContractsThatsActive();
    }

    public void concludeContract(int rentalContractId) {
        rentalContractRepository.concludeContract(rentalContractId);
    }

    public RentalContract getRentalContract(int rentalContractId) {
        return rentalContractRepository.getRentalContract(rentalContractId);
    }

    public void changeConditionUponReturn(int rentalContractId) {
        rentalContractRepository.changeConditionUponReturn(rentalContractId);
    }

    public void changeConditionUponReturnToDamaged(int rentalContractId) {
        rentalContractRepository.changeConditionUponReturnToDamaged(rentalContractId);
    }

    public List<RentalContract> getAllRentalContracts() {
        return rentalContractRepository.getAllRentalContracts();
    }

    public void deleteRentalContract(int rentalContractId) {
        rentalContractRepository.deleteRentalContract(rentalContractId);
    }
}
