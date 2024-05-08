package com.example.carrentalexam.controllers;

import com.example.carrentalexam.enums.EmployeeUserDepartment;
import com.example.carrentalexam.models.EmployeeUser;
import com.example.carrentalexam.models.RentalContract;
import com.example.carrentalexam.services.CarService;
import com.example.carrentalexam.services.EmployeeUserService;
import com.example.carrentalexam.services.RentalContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeUsersController {

    private final EmployeeUserService employeeUserService;
    private final CarService carService;
    private final RentalContractService rentalContractService;

    // Som patty sagde til vores fremlæggelse er det vigtigt at vi bruger konstruktør i stedet for autowired for at sikre immutabilitet.
    public EmployeeUsersController(EmployeeUserService employeeUserService, CarService carService,
                                   RentalContractService rentalContractService) {
        this.employeeUserService = employeeUserService;
        this.carService = carService;
        this.rentalContractService = rentalContractService; // NY
    }

    @PostMapping("/login")
    public String logIn(@RequestParam String username, @RequestParam String password,
                        Model model) {
        List<EmployeeUser> employeeUsers = employeeUserService.getAllEmployees();
        for (int i = 0; i < employeeUsers.size(); i++) {
            if (employeeUsers.get(i).getUsername().equals(username)) {
                if (employeeUsers.get(i).getPassword().equals(password)) {
                    int employeeId = employeeUsers.get(i).getEmployeeUserId();
                    model.addAttribute(employeeUserService.getEmployee(employeeId));
                    return "home/mainMenu";
                }
                else {
                    model.addAttribute("WrongPassword", "Wrong password");
                    return "home/index";
                }
            }
        }
        model.addAttribute("UserDoesNotExist", "user does not exist");
        return "home/index";
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "home/signIn";
    }

    @PostMapping("/signInAction")
    public String signInAction(@RequestParam String username, @RequestParam String password,
                               @RequestParam EmployeeUserDepartment employeeUserDepartment) {
        employeeUserService.addEmployee(username, password, employeeUserDepartment);
        return "redirect:/";
    }

    @GetMapping("/mainMenuDataRegistration")
    public String dataRegistration(@RequestParam int employeeUserId, Model model) {
        model.addAttribute(employeeUserService.getEmployee(employeeUserId)); // for at få brugerens navn til overskriften
        model.addAttribute(carService.getAllCars()); // Giver medarbejderen overblik over alle registrerede biler
        return "home/mainMenuDataRegistration";
    }

   /* @GetMapping("/mainMenuBusinessDeveloper")
    public String businessDeveloper(@RequestParam int employeeUserId, Model model) {
        model.addAttribute(employeeUserService.getEmployee(employeeUserId)); // for at få brugerens navn til overskriften
        model.addAttribute(carService.getAllCars()); // Giver medarbejderen overblik over alle registrerede biler
        return "home/mainMenuBusinessDeveloper";
    }

    */

    @GetMapping("/mainMenuBusinessDeveloper") // NY
    public String businessDeveloper(@RequestParam int employeeUserId, Model model) {
        model.addAttribute(employeeUserService.getEmployee(employeeUserId));
        model.addAttribute("employeeUserId", employeeUserId);
        // int rentedCarsCount = rentalContractService.getRentedCarsCount();
        List<RentalContract> rentedCars = rentalContractService.getRentedCarsCount();
        double totalRevenue = rentalContractService.getTotalRevenue();
        model.addAttribute("rentedCarsCount", rentedCars.size());
        model.addAttribute("totalRevenue", totalRevenue);
        return "home/mainMenuBusinessDeveloper";
    }

}
