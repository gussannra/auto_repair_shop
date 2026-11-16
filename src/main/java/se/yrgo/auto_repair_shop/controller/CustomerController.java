package se.yrgo.auto_repair_shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class CustomerController {

    // Skapa en kund
    @PostMapping("/customers")
    public String createCustomer(@RequestParam(required = true) String name, @RequestParam(required = true) String phoneNumber) {

    }

    // Hämnta kund-ID via namn
    @GetMapping("/customer-id")
    public String getByName(@RequestParam(required = true) String name) {

    }

    // Returnera en lista av alla kunder med deras fordon
    @GetMapping("/customers")
    public List<Object[]> getAllCustomersWithAllVehicles() {

    }
}
