package com.example.carrentalexam.controllers;

import com.example.carrentalexam.models.RentalContract;
import com.example.carrentalexam.services.CarService;
import com.example.carrentalexam.services.DamageService;
import com.example.carrentalexam.services.RentalContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DamagesController {

    private final CarService carService;
    private final DamageService damageService;
    private final RentalContractService rentalContractService;
    private int amountOfDamages = 0; // Bruges til at brugeren kan se hvor mange skader de har lavet til den enkelte bil, da siden bliver genindlæst efter hvert submit.


    public DamagesController(CarService carService ,DamageService damageService, RentalContractService rentalContractService) {
        this.carService = carService;
        this.damageService = damageService;
        this.rentalContractService = rentalContractService;

    }

    @GetMapping("/processCarForDamages")
    public String processCarForDamages(@RequestParam int carId, @RequestParam int rentalContractId ,@RequestParam int employeeUserId ,Model model) {
        model.addAttribute("car", carService.getCarRented(carId));
        model.addAttribute("rentalContractId", rentalContractId);
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/processCar";
    }

    @GetMapping("/processCarYes")
    public String carHasDamages(@RequestParam int carId, @RequestParam int rentalContractId ,@RequestParam int employeeUserId,
                                @RequestParam(required = false) String message, Model model) {
        rentalContractService.changeConditionUponReturnToDamaged(rentalContractId);
        model.addAttribute("car", carService.getCarRented(carId));
        model.addAttribute("rentalContractId", rentalContractId);
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        model.addAttribute("amountOfDamages", amountOfDamages);

    return "home/createDamageReport";
    }

    @PostMapping("/processCarYesAction")
    public String carHasDamages(@RequestParam int rentalContractId, @RequestParam String descriptionOfDamage,
                                @RequestParam double repairCosts, @RequestParam String status,
                                @RequestParam int employeeUserId, @RequestParam int carId) {
        try {
            damageService.createDamageReport(rentalContractId, descriptionOfDamage, repairCosts, status);
            amountOfDamages++;
            return "redirect:/processCarYes?carId=" + carId + "&rentalContractId=" +
                    rentalContractId + "&employeeUserId=" + employeeUserId + "&message=Damage+report+has+been+created.";
        } catch (Exception e) {
            return "redirect:/processCarYes?carId=" + carId + "&rentalContractId=" +
                    rentalContractId + "&employeeUserId=" + employeeUserId + "&message=Something+went+wrong.+Please+try+agian.";
        }

    }

    @PostMapping("/processCarComplete")
    public String processCarComplete(@RequestParam int carId ,@RequestParam int employeeUserId) {
        carService.changeCarToMaintenance(carId);
        amountOfDamages = 0; // Nulstilles efter brugeren ikke har flere skader til bilen.
        return "redirect:/mainMenuDamageAndRepair?employeeUserId=" + employeeUserId;
    }

    @PostMapping("/processCarNo")
    public String carHasNoDamages(@RequestParam int carId, @RequestParam int rentalContractId, @RequestParam int employeeUserId) {
        carService.changeCarToAvailable(carId);
        rentalContractService.concludeContract(rentalContractId);
        RentalContract rentalContract = rentalContractService.getRentalContract(rentalContractId);
        if (rentalContract.getConditionUponReturn().startsWith("to")) { // Alle kontrakter som er aktive har "to be determined" i deres 'condition_upon_return"
            rentalContractService.changeConditionUponReturn(rentalContractId);
            // Hvis ingen skader rapporteres, så bliver 'condition_upon_return' på lejekontrakten til = "same condition as before"
        }

        return "redirect:/mainMenuDamageAndRepair?employeeUserId=" + employeeUserId;
    }

    @PostMapping("/damageReportedToProcessed")
    public String damageReportedToProcessed(@RequestParam int damageId, @RequestParam int employeeUserId) {
        damageService.changeDamageFromReportedToProcessed(damageId);
        return "redirect:/mainMenuDamageAndRepair?employeeUserId=" + employeeUserId;

    }
}
