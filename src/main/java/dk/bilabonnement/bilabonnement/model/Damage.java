package dk.bilabonnement.bilabonnement.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class Damage {
    private Integer damageId;
    private Integer reportId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Positive
    private BigDecimal price;

    public Damage() {
    }

    public Damage(Integer damageId, Integer reportId, String description, BigDecimal price) {
        this.damageId = damageId;
        this.reportId = reportId;
        this.description = description;
        this.price = price;
    }

    public Integer getDamageId() {
        return damageId;
    }

    public void setDamageId(Integer damageId) {
        this.damageId = damageId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
