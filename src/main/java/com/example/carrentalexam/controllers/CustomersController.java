package com.example.carrentalexam.controllers;

import com.example.carrentalexam.services.CarService;
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
    public String createNewCustomer(@RequestParam int employeeUserId, @RequestParam(required = false) String message, Model model) {
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        return "home/createNewCustomer";
    }

    @PostMapping("/createNewCustomerAction")
    public String createNewCustomer(@RequestParam String name, @RequestParam String address,
                                    @RequestParam String number, @RequestParam String email,
                                    @RequestParam int employeeUserId, Model model) {
        try {
            customerService.createNewCustomer(name, address, number, email);
            return "redirect:/createNewCustomer?employeeUserId=" + employeeUserId + "&message=Customer+has+been+created";
        } catch (Exception e) {
            return "redirect:/createNewCustomer?employeeUserId=" + employeeUserId + "&message=Something+went+wrong.+Please+try+agian.";
        }
    }

    @GetMapping("/updateCustomer")
    public String updateCustomer(@RequestParam int employeeUserId, @RequestParam (required = false) String message, Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        return "home/updateCustomer";
    }

    @PostMapping("/updateCustomerAction")
    public String updateCustomer(@RequestParam String name, @RequestParam String address, @RequestParam String number,
                                 @RequestParam String email, @RequestParam int customerId, @RequestParam int employeeUserId) {
        try {
            customerService.updateCustomer(name, address, number, email, customerId);
            return "redirect:/updateCustomer?employeeUserId=" + employeeUserId + "&message=Customer+has+been+updated";
        } catch (Exception e) {
            return "redirect:/updateCustomer?employeeUserId=" + employeeUserId + "&message=Something+went+wrong.+Try+again";
        }
    }

    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam int employeeUserId, @RequestParam (required = false) String message, Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employeeUserId", employeeUserId);
        model.addAttribute("message", message);
        return "home/deleteCustomer";
    }
    @GetMapping("/deleteCustomerConfirm")
    public String deleteCustomerConfirm(@RequestParam int customerId, @RequestParam int employeeUserId, Model model) {
        model.addAttribute("customer", customerService.getCustomer(customerId));
        model.addAttribute("employeeUserId", employeeUserId);
        return "home/deleteCustomerConfirm";

    }

    @PostMapping("/deleteCustomerAction")
    public String deleteCustomer(@RequestParam int customerId, @RequestParam int employeeUserId) {
        customerService.deleteCustomer(customerId);
        return "redirect:/deleteCustomer?employeeUserId=" + employeeUserId + "&message=Customer+has+been+deleted";
    }



}
