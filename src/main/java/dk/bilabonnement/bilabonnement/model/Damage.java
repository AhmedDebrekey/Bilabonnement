package dk.bilabonnement.bilabonnement.model;

import java.math.BigDecimal;

public class Damage {
    private Integer damageId;
    private Integer reportId;
    private String description;
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
