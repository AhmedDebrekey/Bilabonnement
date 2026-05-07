package dk.bilabonnement.bilabonnement.model;

public class Car {
    private Integer carId;
    private String licensePlate;
    private String chassisNumber;
    private String brand;
    private String model;
    private String color;
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

