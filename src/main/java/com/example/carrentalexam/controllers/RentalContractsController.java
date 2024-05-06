package com.example.carrentalexam.controllers;

import com.example.carrentalexam.services.RentalContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RentalContractsController {
    private final RentalContractService rentalContractService;

    public RentalContractsController(RentalContractService rentalContractService) {
        this.rentalContractService = rentalContractService;
    }

    @GetMapping("/createRentalContract")
    public String createRentalContract(@RequestParam int employeeUserId, Model model) {
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/createNewRentalContract";
    }
}
