package se.yrgo.auto_repair_shop.dto;

public class VehicleBrandDto {
    private String registrationNumber;
    private String brand;

    public VehicleBrandDto() {

    }

    public VehicleBrandDto(String registrationNumber, String brand) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }
}
