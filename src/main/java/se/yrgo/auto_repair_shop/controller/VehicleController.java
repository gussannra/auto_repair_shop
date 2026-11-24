package se.yrgo.auto_repair_shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.auto_repair_shop.domain.Customer;
import se.yrgo.auto_repair_shop.domain.Vehicle;
import se.yrgo.auto_repair_shop.dto.VehicleBrandDto;
import se.yrgo.auto_repair_shop.dto.VehicleDto;
import se.yrgo.auto_repair_shop.repository.CustomerRepository;
import se.yrgo.auto_repair_shop.repository.VehicleRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class VehicleController {
    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;

    public VehicleController(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    // Skapa en fordon
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDto> createVehicle(@RequestParam String registrationNumber,
                                                    @RequestParam String brand,
                                                    @RequestParam String model,
                                                    @RequestParam int productionYear) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(registrationNumber);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setProductionYear(productionYear);

        Vehicle saved = vehicleRepository.save(vehicle);

        VehicleDto response = new VehicleDto(
                saved.getId(),
                saved.getRegistrationNumber(),
                saved.getBrand(),
                saved.getModel(),
                saved.getProductionYear());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Returnera en lista av alla fordon
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        List<VehicleDto> vehicles = vehicleRepository.findAll().stream().map(vehicle -> new VehicleDto(
                vehicle.getId(),
                vehicle.getRegistrationNumber(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getProductionYear())).toList();

        return ResponseEntity.ok(vehicles);

    }

    // Returnera en lista av alla fordon med visst bilmärke (brand)
    @GetMapping("/vehicles-by-brand")
    public ResponseEntity<List<VehicleDto>> getByBrand(@RequestParam String brand) {
        List<VehicleDto> vehicles = vehicleRepository.findByBrand(brand)
                .stream().map(vehicle -> new VehicleDto(
                        vehicle.getId(),
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getProductionYear())).toList();

        return ResponseEntity.ok(vehicles);
    }

    // Koppla fordon till kund
    @PostMapping("/addvehicletocustomer")
    public ResponseEntity<String> addVehicleToCustomer(@RequestParam Long customerId, @RequestParam Long vehicleId) {

        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);

        if (customerOpt.isEmpty() || vehicleOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid customer or vehicle");
        }

        Customer customer = customerOpt.get();
        Vehicle vehicle = vehicleOpt.get();

        customer.addVehicle(vehicle);
        customerRepository.save(customer);
        return ResponseEntity.ok("Vehicle connected to customer successfully");

    }

    // Uppdatera bilmärket för ett fordon.
    @PutMapping("/vehicles/{id}/brand")
    public ResponseEntity<String> updateBrandById(@PathVariable Long id, @RequestParam String brand) {

        int updated = vehicleRepository.updateBrandById(id, brand);
        return ResponseEntity.ok(updated + " vehicle updated.");
    }

    // Lista registreringsnummber och bilmärke
    @GetMapping("/vehicle-brand-info")
    public ResponseEntity<List<VehicleBrandDto>> projection() {

        List<VehicleBrandDto> vehicles = vehicleRepository.findAllVehiclesBrandAndRegNumber()
                .stream().map(vehicle -> new VehicleBrandDto(
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand())).toList();

        return ResponseEntity.ok(vehicles);
    }

    // Hämta alla fordon för en viss kund baserat på kundens ID
    @GetMapping("/vehicles-by-customerId")
    public ResponseEntity<List<VehicleDto>> findVehiclesByCustomerId(@RequestParam Long customerId) {

        List<VehicleDto> vehicles = vehicleRepository.findVehiclesByCustomerId(customerId)
                .stream().map(vehicle -> new VehicleDto(
                        vehicle.getId(),
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getProductionYear())).toList();

        return ResponseEntity.ok(vehicles);
    }
}
