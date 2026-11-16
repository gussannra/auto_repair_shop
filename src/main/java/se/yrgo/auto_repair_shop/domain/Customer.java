package se.yrgo.auto_repair_shop.domain;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Vehicle> vehicleList = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    public Set<Vehicle> getVehicleList() {
        Set<Vehicle> unmodifiable = Collections.unmodifiableSet(this.vehicleList);
        return unmodifiable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phoneNumber = phone;
    }
}
