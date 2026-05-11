package dk.bilabonnement.bilabonnement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.beans.Transient;

public class Car {
    private Integer carId;

    @NotBlank(message = "License plate is required")
    @Size(max=16, message = "Licence plate must be maximum 16 chars long")
    private String licensePlate;

    @NotBlank(message = "Chassis number is required")
    @Size(max =17, message = "Chassis number must be maximum 17 chars long") // for my sake I am making it so that it can be less than 17
    private String chassisNumber;

    @NotBlank(message = "Branch is required")
    private String brand;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Color is required")
    private String color;

    @NotBlank(message = "Status is required")
    private String status;

    public Car() {
    }
    public Car(Integer car_id, String license_plate, String chassis_number, String brand, String model, String color, String status) {
        this.carId = car_id;
        this.licensePlate = license_plate;
        this.chassisNumber = chassis_number;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.status = status;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

