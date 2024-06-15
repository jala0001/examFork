package com.example.carrentalexam.controllers;

import com.example.carrentalexam.models.*;
import com.example.carrentalexam.services.CarService;
import com.example.carrentalexam.services.CustomerService;
import com.example.carrentalexam.services.RentalContractService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Controller
public class RentalContractsController {
    private final RentalContractService rentalContractService;
    private final CustomerService customerService;
    private final CarService carService;

    public RentalContractsController(RentalContractService rentalContractService, CustomerService customerService,
                                     CarService carService) {
        this.rentalContractService = rentalContractService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping("/createRentalContract")
    public String createRentalContract(@RequestParam int employeeUserId, @RequestParam(required = false) String message,  Model model) {
        model.addAttribute("employeeUserId", employeeUserId);
        List<Customer> customers = customerService.getAllCustomers();
        List<Car> cars = carService.getAllCarsThatAreAvailable();
        List<Location> locations = rentalContractService.getAllLocations();
        Collections.sort(customers, new CustomerNameComparator());
        Collections.sort(cars, new CarBrandComparator());
        model.addAttribute("customers", customers);
        model.addAttribute("cars", cars);
        model.addAttribute("locations", locations);
        model.addAttribute("message", message);
        return "home/createNewRentalContract";
    }

    @PostMapping("/createNewRentalContractAction")
    public String createRentalContract(@RequestParam int customerId, @RequestParam int carId,
                                       @RequestParam LocalDate startDate, @RequestParam LocalDate endDate,
                                       @RequestParam double price, @RequestParam int locationId,
                                       @RequestParam String conditionOnDelivery, @RequestParam String conditionUponReturn,
                                       @RequestParam int employeeUserId) {
        try {
            rentalContractService.createRentalContract(customerId, carId, startDate, endDate, price,
                    locationId, conditionOnDelivery, conditionUponReturn);
            return "redirect:/createRentalContract?employeeUserId=" + employeeUserId + "&message=Rental+contract+has+been+created.";
        } catch (Exception e) {
            return "redirect:/createRentalContract?employeeUserId=" + employeeUserId + "&message=Something+went+wrong.+Please+try+agian.";
        }
    }


    @PostMapping("/calculatePrice")
    public String calculatePrice(
            @RequestParam("carId") int carId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @RequestParam("employeeUserId") int employeeUserId,
            @RequestParam("customerId") int customerId,
            @RequestParam("locationId") int locationId, // Ændret fra 'pickUpLocation' til 'locationId'
            @RequestParam("conditionOnDelivery") String conditionOnDelivery,
            RedirectAttributes redirectAttributes) {

        double monthlyPrice = carService.getMonthlyPriceForCar(carId);
        double pricePerDay = monthlyPrice / 30; // Antager at der er 30 dage i en måned for enkelheds skyld
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        double priceForRentalContract = pricePerDay * daysBetween;

        // Tilføj alle nødvendige attributter til redirectAttributes
        redirectAttributes.addFlashAttribute("price", priceForRentalContract);
        redirectAttributes.addFlashAttribute("customerId", customerId);
        redirectAttributes.addFlashAttribute("carId", carId);
        redirectAttributes.addFlashAttribute("startDate", startDate.toString());
        redirectAttributes.addFlashAttribute("endDate", endDate.toString());
        redirectAttributes.addFlashAttribute("locationId", locationId); // Opdateret til at bruge 'locationId'
        redirectAttributes.addFlashAttribute("conditionOnDelivery", conditionOnDelivery);
        redirectAttributes.addFlashAttribute("employeeUserId", employeeUserId);

        return "redirect:/createRentalContract?employeeUserId=" + employeeUserId;
    }


    @GetMapping("/deleteRentalContract")
    public String deleteRentalContract(@RequestParam int employeeUserId, @RequestParam (required = false) String message, Model model) {
        model.addAttribute("rentalContracts", rentalContractService.getAllRentalContracts());
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/deleteRentalContract";

    }

    @GetMapping("/deleteRentalContractConfirm")
    public String deleteRentalContractConfirm(@RequestParam int rentalContractId, @RequestParam int employeeUserId, Model model) {
        model.addAttribute("rentalContract", rentalContractService.getRentalContract(rentalContractId));
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/deleteRentalContractConfirm";
    }

    @PostMapping("/deleteRentalContractAction")
    public String deleteRentalContract(@RequestParam int rentalContractId, @RequestParam int employeeUserId, @RequestParam int carId) {
        rentalContractService.deleteRentalContract(rentalContractId);
        carService.changeCarToAvailable(carId);
        return "redirect:/deleteRentalContract?employeeUserId=" + employeeUserId + "&message=Rental+contract+has+been+deleted";
    }

}
