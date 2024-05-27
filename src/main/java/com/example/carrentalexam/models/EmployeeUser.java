package com.example.carrentalexam.models;

import com.example.carrentalexam.enums.EmployeeUserDepartment;

public class EmployeeUser {
    private int employeeUserId;
    private String username;
    private String password;
    private EmployeeUserDepartment department;

    public EmployeeUser() {

    }




    public int getEmployeeUserId() {
        return employeeUserId;
    }

    public void setEmployeeUserId(int employeeUserId) {
        this.employeeUserId = employeeUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EmployeeUserDepartment getDepartment() {
        return department;
    }

    public void setDepartment(EmployeeUserDepartment department) {
        this.department = department;
    }
}
