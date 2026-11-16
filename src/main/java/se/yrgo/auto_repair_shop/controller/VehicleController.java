package se.yrgo.auto_repair_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.auto_repair_shop.domain.Vehicle;

import java.util.List;

@Controller
public class VehicleController {

    // Skapa en fordon
    @PostMapping("/vehicles")
    public String createVehicle(@RequestParam(required = true) String registrationNumber,
                                @RequestParam(required = true) String brand,
                                @RequestParam(required = true) String model,
                                @RequestParam(required = true) int productionYear) {

    }

    // Returnera en lista av alla fordon
    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {

    }

    // Returnera en lista av alla fordon med visst bilmärke (brand)
    @GetMapping("/vehicles-by-brand")
    public List<Vehicle> getByBrand(@RequestParam(required = true) String brand) {

    }

    // Koppla fordon till kund
    @PostMapping("/addvehicletocustomer")
    public String addVehicleToCustomer(@RequestParam(required = true) Long customerId, @RequestParam(required = true) Long vehicleId) {

    }

    // Uppdatera bilmärket för ett fordon. Använd repository-metoden som du skapade med @Modifying
    @PutMapping("/vehicles")
    public String updateBrandById(@RequestParam(required = true) Long id, @RequestParam(required = true) String brand) {

    }

    // Lista registreringsnummber och bilmärke

}
