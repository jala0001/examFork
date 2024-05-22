package com.example.carrentalexam.controllers;

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
        model.addAttribute("customers", customerService.getAllCustomers()); // så vi har et overblik over eksisterende kunder når man opretter en lejekontrakt
        model.addAttribute("cars", carService.getAllCarsThatAreAvailable()); // så man ikke kan leje en bil som er udlejet i forvejen
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

            return "redirect:/createRentalContract?employeeUserId=" + employeeUserId + "&message=Rental+contract+has+been+created."; // Redirect til EmployeeUserController
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
            @RequestParam("customerId") int customerId,  // Antag at dette også sendes fra formen
            @RequestParam("pickUpLocation") String pickUpLocation,
            @RequestParam("conditionOnDelivery") String conditionOnDelivery,
            RedirectAttributes redirectAttributes) {

        double monthlyPrice = carService.getMonthlyPriceForCar(carId);
        double pricePerDay = monthlyPrice / 30;
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        double priceForRentalContract = pricePerDay * daysBetween;

        // Tilføjer den beregnede pris og alle andre indtastede værdier til RedirectAttributes
        redirectAttributes.addFlashAttribute("price", priceForRentalContract);
        redirectAttributes.addFlashAttribute("customerId", customerId);
        redirectAttributes.addFlashAttribute("carId", carId);
        redirectAttributes.addFlashAttribute("startDate", startDate.toString());
        redirectAttributes.addFlashAttribute("endDate", endDate.toString());
        redirectAttributes.addFlashAttribute("pickUpLocation", pickUpLocation);
        redirectAttributes.addFlashAttribute("conditionOnDelivery", conditionOnDelivery);
        redirectAttributes.addFlashAttribute("employeeUserId", employeeUserId);

        return "redirect:/createRentalContract?employeeUserId=" + employeeUserId;
    }


}
