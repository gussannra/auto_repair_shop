package se.yrgo.auto_repair_shop.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<VehicleDto> vehicleList = new ArrayList<>();

    public CustomerDto() {
    }

    public CustomerDto(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public List<VehicleDto> getVehicleList() {
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

    public void setVehicleList(List<VehicleDto> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
