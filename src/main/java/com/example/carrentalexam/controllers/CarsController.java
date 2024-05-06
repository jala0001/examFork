package com.example.carrentalexam.controllers;

import com.example.carrentalexam.services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/createNewCar")
    public String createNewCar(@RequestParam int employeeUserId, Model model) {
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/createNewCar";
    }
    @PostMapping("/createNewCarAction")
    public String createNewCar(@RequestParam String frameNumber, @RequestParam String brand,
                               @RequestParam String model, @RequestParam String registrationNumber,
                               @RequestParam String status, @RequestParam int employeeUserId) {
        carService.createNewCar(frameNumber, brand, model, registrationNumber, status);
        return "redirect:/mainMenuDataRegistration?employeeUserId=" + employeeUserId; // redirectes til EmployeeUsersController

    }
}
