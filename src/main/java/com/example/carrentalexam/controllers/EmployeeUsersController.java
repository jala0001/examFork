package com.example.carrentalexam.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeUsersController {

    @PostMapping("/signin")
    public String signIn() {
        return "redirect:/";
    }
}
