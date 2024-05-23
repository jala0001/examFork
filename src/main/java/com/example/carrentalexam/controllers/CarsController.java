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
    public String createNewCar(@RequestParam int employeeUserId, @RequestParam(required = false) String message, Model model) {
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        return "home/createNewCar";
    }
    @PostMapping("/createNewCarAction")
    public String createNewCar(@RequestParam String frameNumber, @RequestParam String brand,
                               @RequestParam String model, @RequestParam int monthlyPrice, @RequestParam String registrationNumber,
                               @RequestParam String status, @RequestParam int employeeUserId) {
        try {
            carService.createNewCar(frameNumber, brand, model, monthlyPrice, registrationNumber, status);
            return "redirect:/createNewCar?employeeUserId=" + employeeUserId + "&message=Car+has+been+created";
        } catch (Exception e) {
            return "redirect:/createNewCar?employeeUserId=" + employeeUserId + "&message=Something+went+wrong.+Please+try+agian.";

        }

    }

    @GetMapping("/updateCar")
    public String updateCar(@RequestParam int employeeUserId, @RequestParam (required = false) String message, Model model) {
        model.addAttribute("cars", carService.getAllCarsThatAreAvailable()); // så vi ikke kan lave ændringer på udlejede biler.
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        return "home/updateCar";
    }

    @PostMapping("/updateCarAction")
    public String updateCar(@RequestParam String frameNumber, @RequestParam String brand, @RequestParam String model,
                            @RequestParam double monthlyPrice, @RequestParam String registrationNumber,
                            @RequestParam String status, @RequestParam int carId, @RequestParam int employeeUserId) {
        try {
           carService.updateCar(frameNumber, brand, model, monthlyPrice, registrationNumber, status, carId);
            return "redirect:/updateCar?employeeUserId=" + employeeUserId + "&message=Car+has+been+updated";
        } catch (Exception e) {
            return "redirect:/updateCar?employeeUserId=" + employeeUserId + "&message=Something+went+wrong.+Try+again";
        }
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam int employeeUserId, @RequestParam (required = false) String message, Model model) {
        model.addAttribute("cars", carService.getAllCarsThatAreAvailable()); // igen så man kun kan slette biler der ikke er udlejet.
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        return "home/deleteCar";
    }
    @GetMapping("/deleteCarConfirm")
    public String deleteCarConfirm(@RequestParam int carId, @RequestParam int employeeUserId, Model model) {
        model.addAttribute("car", carService.getCar(carId));
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/deleteCarConfirm";

    }

    @PostMapping("/deleteCarAction")
    public String deleteCar(@RequestParam int carId, @RequestParam int employeeUserId) {
        carService.deleteCar(carId);
        return "redirect:/deleteCar?employeeUserId=" + employeeUserId + "&message=Car+has+been+deleted";
    }

}
