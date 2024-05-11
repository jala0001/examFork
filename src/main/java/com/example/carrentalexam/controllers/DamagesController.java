package com.example.carrentalexam.controllers;

import com.example.carrentalexam.services.CarService;
import com.example.carrentalexam.services.DamageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamagesController {

    private final CarService carService;
    private final DamageService damageService;

    public DamagesController(CarService carService ,DamageService damageService) {
        this.carService = carService;
        this.damageService = damageService;
    }

    @GetMapping("/processCarForDamages")
    public String processCarForDamages(@RequestParam int carId, Model model) {
        model.addAttribute("car", carService.getCar(carId));
        return "home/processCar";
    }

    @GetMapping("/processCarYes")
    public String CarHasDamages() {
    return "home/todoPage";
    }
}
