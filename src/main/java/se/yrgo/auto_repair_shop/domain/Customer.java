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

    @OneToMany(mappedBy = "customer",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private List<Vehicle> vehicleList = new ArrayList<>();

    public Customer() {
    }

    public Customer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
        vehicle.setCustomer(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(vehicleList, customer.vehicleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, vehicleList);
    }
}
