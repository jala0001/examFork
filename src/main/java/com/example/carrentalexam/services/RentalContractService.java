package com.example.carrentalexam.services;

import com.example.carrentalexam.repositories.RentalContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalContractService {
    @Autowired
    private RentalContractRepository rentalContractRepository;

    public void createRentalContract(int customerId, int carId, LocalDate startDate,
                                     LocalDate endDate, double price, String pickUpLocation,
                                     String conditionOnDelivery, String conditionUponReturn) {
    rentalContractRepository.createRentalContract(customerId, carId, startDate, endDate, price,
            pickUpLocation, conditionOnDelivery, conditionUponReturn);
    }
}
