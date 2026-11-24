package se.yrgo.auto_repair_shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.auto_repair_shop.domain.Customer;
import se.yrgo.auto_repair_shop.domain.Vehicle;
import se.yrgo.auto_repair_shop.dto.CustomerDto;
import se.yrgo.auto_repair_shop.dto.VehicleDto;
import se.yrgo.auto_repair_shop.repository.CustomerRepository;
import se.yrgo.auto_repair_shop.repository.VehicleRepository;

import java.util.*;

@RestController
public class CustomerController {

    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;

    public CustomerController(VehicleRepository vehicleRepository, CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    // Skapa en kund
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestParam String name, @RequestParam String phone) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phone);

        Customer saved = customerRepository.save(customer);

        CustomerDto response = new CustomerDto(
                saved.getId(),
                saved.getName(),
                saved.getPhoneNumber());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Hämnta kund-ID via namn
    @GetMapping("/customer-id")
    public ResponseEntity<Long> getByName(@RequestParam String name) {

        return customerRepository.findByName(name)
                .map(Customer::getId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Returnera en lista av alla kunder med deras fordon
    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomersWithAllVehicles() {
        List<Object[]> all = customerRepository.findAllCustomerWithAllVehicles();
        Map<Long, CustomerDto> customerMap = new HashMap<>();

        for (Object[] result : all) {
            Customer customer = (Customer) result[0];
            Vehicle vehicle = (Vehicle) result[1];

            CustomerDto customerDto = customerMap.getOrDefault(customer.getId(), new CustomerDto());
            customerDto.setId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setPhoneNumber(customer.getPhoneNumber());

            if (vehicle != null) {
                VehicleDto vehicleDto = new VehicleDto();
                vehicleDto.setId(vehicle.getId());
                vehicleDto.setRegistrationNumber(vehicle.getRegistrationNumber());
                vehicleDto.setBrand(vehicle.getBrand());
                vehicleDto.setModel(vehicle.getModel());
                vehicleDto.setProductionYear(vehicle.getProductionYear());

                customerDto.getVehicleList().add(vehicleDto);
            }

            customerMap.put(customer.getId(), customerDto);
        }

        return new ArrayList<>(customerMap.values());
    }
}
