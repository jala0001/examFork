package com.example.carrentalexam.controllers;

import com.example.carrentalexam.enums.EmployeeUserDepartment;
import com.example.carrentalexam.models.*;
import com.example.carrentalexam.services.CarService;
import com.example.carrentalexam.services.DamageService;
import com.example.carrentalexam.services.EmployeeUserService;
import com.example.carrentalexam.services.RentalContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeUsersController {

    private final EmployeeUserService employeeUserService;
    private final CarService carService;
    private final RentalContractService rentalContractService;
    private final DamageService damageService;


    public EmployeeUsersController(EmployeeUserService employeeUserService, CarService carService,
                                   RentalContractService rentalContractService, DamageService damageService) {
        this.employeeUserService = employeeUserService;
        this.carService = carService;
        this.rentalContractService = rentalContractService;
        this.damageService = damageService;
    }

    @PostMapping("/login")
    public String logIn(@RequestParam String username, @RequestParam String password, Model model) {
        EmployeeUser user = employeeUserService.authenticateUser(username, password);
        if (user != null) {
            model.addAttribute("employeeUser", user);
            return "home/mainMenu";
        } else {
            model.addAttribute("loginError", "Invalid username or password");
            return "home/index";
        }
    }

    @GetMapping("/signIn")
    public String signIn(@RequestParam(required = false) String message, Model model) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "home/signIn";
    }

    @PostMapping("/signInAction")
    public String signInAction(@RequestParam String username, @RequestParam String password,
                               @RequestParam EmployeeUserDepartment employeeUserDepartment) {
        try {
            employeeUserService.addEmployee(username, password, employeeUserDepartment);
            return "redirect:/signIn?message=User+has+been+created.";
        } catch (Exception e) {
            return "redirect:/signIn?message=Something+went+wrong.+Username and password can max be 100 characters.";
        }

    }

    @GetMapping("/mainMenuDataRegistration")
    public String dataRegistration(@RequestParam int employeeUserId, Model model) {
        model.addAttribute(employeeUserService.getEmployee(employeeUserId));
        model.addAttribute(carService.getAllCars());
        return "home/mainMenuDataRegistration";
    }

    @GetMapping("/mainMenuDamageAndRepair")
    public String damageAndRepair(@RequestParam int employeeUserId, Model model) {
        List<RentalContract> rentalContractsReturned = rentalContractService.getAllRentalContractWhereTheCarHasBeenReturned();
        List<CarWithContract> carsFromRentalContractsReturned = new ArrayList<>();

        for (RentalContract contract : rentalContractsReturned) { // Tjekker igennem for biler som er klar til at blive behandlet
            Car car = carService.getCarRented(contract.getCarId());
            if (car != null) {
                boolean carAlreadyInList = false;
                for (CarWithContract carWithContract : carsFromRentalContractsReturned) {
                    if (carWithContract.getCar().getCarId() == (car.getCarId())) {
                        carAlreadyInList = true;
                        break;
                    }
                }
                if (!carAlreadyInList) {
                    carsFromRentalContractsReturned.add(new CarWithContract(car, contract.getRentalContractId()));
                }
            }
        }


        List<RentalContract> allRentalContracts = rentalContractService.getAllRentalContractsThatsActive();
        List<CarWithContract> carsInMaintenanceWithDamages = new ArrayList<>();

        for (RentalContract contract : allRentalContracts) { // Tjekker igennem biler som har skader og er klar til at blive udbedret
            Car car = carService.getCarMaintenance(contract.getCarId());
            if (car != null) {
                List<Damage> damages = damageService.getDamagesByContractId(contract.getRentalContractId());
                carsInMaintenanceWithDamages.add(new CarWithContract(car, contract.getRentalContractId(), damages));
            }
        }


        model.addAttribute("rentalContractCarsReturned", carsFromRentalContractsReturned);
        model.addAttribute("carsInMaintenanceWithDamages", carsInMaintenanceWithDamages);
        model.addAttribute("employeeUserId", employeeUserId);

        return "home/mainMenuDamageAndRepair";
    }




    @GetMapping("/mainMenuBusinessDeveloper")
    public String businessDeveloper(@RequestParam int employeeUserId, Model model) {
        try {

            List<Car> rentedCars = rentalContractService.getRentedCarsCount();
            double totalRevenue = rentalContractService.getTotalRevenue();
            List<CustomerCarAndRentalContract> customerCarAndRentalContracts = rentalContractService.getAllCustomerCarAndRentalContract();
            List<Car> carsThatAreAvailable = carService.getAllCarsThatAreAvailable();
           // List<Car> carsThatAreMaintenance = carService.getAllCarsThatAreMaintenance();

            model.addAttribute(employeeUserService.getEmployee(employeeUserId));
            model.addAttribute("employeeUserId", employeeUserId);
            model.addAttribute("rentedCarsCount", rentedCars.size());
            model.addAttribute("totalRevenue", totalRevenue);
            model.addAttribute("customerCarAndRentalContracts", customerCarAndRentalContracts);
            model.addAttribute("carsThatAreAvailable", carsThatAreAvailable);
           // model.addAttribute("carsThatAreMaintenance", carsThatAreMaintenance);

            return "home/mainMenuBusinessDeveloper";
        } catch (NullPointerException e) {
            model.addAttribute("error", "Data not found or incomplete data");
            return "redirect:/mainMenuBusinessDeveloper?employeeUserId=" + employeeUserId;
        } catch (Exception e) {
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "redirect:/mainMenuBusinessDeveloper?employeeUserId=" + employeeUserId;
        }
    }


}
