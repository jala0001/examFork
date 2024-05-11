package com.example.carrentalexam.services;

import com.example.carrentalexam.enums.CarStatus;
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
    private CarRepository carRepository; // NY

    public RentalContractService(RentalContractRepository rentalContractRepository) { // NY
        this.rentalContractRepository = rentalContractRepository;
    }

    public void createRentalContract(int customerId, int carId, LocalDate startDate, // NY
                                     LocalDate endDate, double price, String pickUpLocation,
                                     String conditionOnDelivery, String conditionUponReturn) {

        rentalContractRepository.createRentalContract(customerId, carId, startDate, endDate, price,
                pickUpLocation, conditionOnDelivery, conditionUponReturn);

        carRepository.updateCarStatus(carId, CarStatus.RENTED.name());
    }


    public List<RentalContract> getRentedCarsCount() { // NY
        return rentalContractRepository.getRentedCarsCount();
    }

    public double getTotalRevenue() { // NY
        return rentalContractRepository.getTotalRevenue();
    }

    public List<RentalContract> getAllRentalContractWhereTheCarHasBeenReturned() {
        return rentalContractRepository.getAllRentalContractWhereTheCarHasBeenReturned();
    }
}
