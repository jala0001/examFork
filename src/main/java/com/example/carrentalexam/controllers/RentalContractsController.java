package com.example.carrentalexam.controllers;

import com.example.carrentalexam.models.Car;
import com.example.carrentalexam.models.CarBrandComparator;
import com.example.carrentalexam.models.Customer;
import com.example.carrentalexam.models.CustomerNameComparator;
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
        Collections.sort(customers, new CustomerNameComparator());
        Collections.sort(cars, new CarBrandComparator());
        model.addAttribute("customers", customers);
        model.addAttribute("cars", cars);
        model.addAttribute("message", message);
        return "home/createNewRentalContract";
    }

    @PostMapping("/createNewRentalContractAction")
    public String createRentalContract(@RequestParam int customerId, @RequestParam int carId,
                                       @RequestParam LocalDate startDate,
                                       @RequestParam LocalDate endDate, @RequestParam double price,
                                       @RequestParam String pickUpLocation,
                                       @RequestParam String conditionOnDelivery,
                                       @RequestParam String conditionUponReturn,
                                       @RequestParam int employeeUserId) {
        try {
            rentalContractService.createRentalContract(customerId, carId, startDate, endDate, price,
                    pickUpLocation, conditionOnDelivery, conditionUponReturn);

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
            @RequestParam("pickUpLocation") String pickUpLocation,
            @RequestParam("conditionOnDelivery") String conditionOnDelivery,
            RedirectAttributes redirectAttributes) {

        double monthlyPrice = carService.getMonthlyPriceForCar(carId);
        double pricePerDay = monthlyPrice / 30;
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        double priceForRentalContract = pricePerDay * daysBetween;


        redirectAttributes.addFlashAttribute("price", priceForRentalContract);
        redirectAttributes.addFlashAttribute("customerId", customerId);
        redirectAttributes.addFlashAttribute("carId", carId);
        redirectAttributes.addFlashAttribute("startDate", startDate.toString());
        redirectAttributes.addFlashAttribute("endDate", endDate.toString());
        redirectAttributes.addFlashAttribute("pickUpLocation", pickUpLocation);
        redirectAttributes.addFlashAttribute("conditionOnDelivery", conditionOnDelivery);
        redirectAttributes.addFlashAttribute("employeeUserId", employeeUserId);
        // redirectAttribues.addFlashAttribute bruges til at allerede indtastede data forbliver på siden efter brugeren for systemet til at udregne den samlede pris.
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
