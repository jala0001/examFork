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
    private boolean firstReport = true;

    public DamagesController(CarService carService ,DamageService damageService) {
        this.carService = carService;
        this.damageService = damageService;

    }

    @GetMapping("/processCarForDamages")
    public String processCarForDamages(@RequestParam int carId, @RequestParam int rentalContractId ,@RequestParam int employeeUserId ,Model model) {
        model.addAttribute("car", carService.getCar(carId));
        model.addAttribute("rentalContractId", rentalContractId);
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/processCar";
    }

    @GetMapping("/processCarYes")
    public String carHasDamages(@RequestParam int carId, @RequestParam int rentalContractId ,@RequestParam int employeeUserId, Model model) {
        model.addAttribute("car", carService.getCar(carId));
        model.addAttribute("rentalContractId", rentalContractId);
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("firstReport", firstReport);
    return "home/createDamageReport";
    }

    @PostMapping("/processCarYesAction")
    public String carHasDamages(@RequestParam int rentalContractId, @RequestParam String descriptionOfDamage,
                                @RequestParam double repairCosts, @RequestParam String status,
                                @RequestParam int employeeUserId, @RequestParam int carId) {
        damageService.createDamageReport(rentalContractId, descriptionOfDamage, repairCosts, status);
        firstReport = false;
        return "redirect:/processCarYes?carId=" + carId + "&rentalContractId=" + rentalContractId + "&employeeUserId=" + employeeUserId;
    }

    @PostMapping("/processCarComplete")
    public String processCarComplete(@RequestParam int carId ,@RequestParam int employeeUserId) {
        carService.changeCarToMaintenance(carId);
        return "redirect:/mainMenuDamageAndRepair?employeeUserId=" + employeeUserId;
    }

    @PostMapping("/processCarNo")
    public String carHasNoDamages(@RequestParam int carId, @RequestParam int employeeUserId) {
        carService.changeCarToAvailable(carId);
        return "redirect:/mainMenuDamageAndRepair?employeeUserId=" + employeeUserId;
    }
}
