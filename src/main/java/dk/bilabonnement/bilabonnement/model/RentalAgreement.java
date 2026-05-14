package dk.bilabonnement.bilabonnement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RentalAgreement {
    private Integer agreementId;
    private Integer carId;
    private Integer customerId;

    @NotNull(message = "Must enter a start date")
    private LocalDate startDate;

    @NotNull(message = "Must enter an end date")
    private LocalDate endDate;

    @NotNull(message = "Monthly price is required")
    @Positive
    private BigDecimal monthlyPrice;

    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    private String status;

    public RentalAgreement() {
    }

    public RentalAgreement(Integer agreementId, Integer carId, Integer customerId, LocalDate startDate, LocalDate endDate, BigDecimal monthlyPrice, String pickupLocation, String status) {
        this.agreementId = agreementId;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyPrice = monthlyPrice;
        this.pickupLocation = pickupLocation;
        this.status = status;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(BigDecimal monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
