package com.example.carrentalexam.controllers;

import com.example.carrentalexam.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomersController {

    private final CustomerService customerService;

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;

    }

    @GetMapping("/createNewCustomer")
    public String createNewCustomer(@RequestParam int employeeUserId, Model model) {
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/createNewCustomer";
    }
    @PostMapping("/createNewCustomerAction")
    public String createNewCustomer(@RequestParam String name, @RequestParam String address,
                                    @RequestParam String number, @RequestParam String email,
                                    @RequestParam int employeeUserId) {
        customerService.createNewCustomer(name, address, number, email);
        return "redirect:/mainMenuDataRegistration?employeeUserId=" + employeeUserId; // redirectes til EmployeeUsersController


    }
}
