package com.example.project;

import android.widget.Spinner;

import java.util.Date;

public class Customer {
    public String accountName;
    public String customerName;
    public String password;
    public String date;
    public String email;
    public String role;
    private Spinner category;

    public Customer(String accountName, String customerName, String password, String date, String email, String role) {
        this.accountName = accountName;
        this.customerName = customerName;
        this.password = password;
        this.date = date;
        this.email = email;
        this.role = role;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
