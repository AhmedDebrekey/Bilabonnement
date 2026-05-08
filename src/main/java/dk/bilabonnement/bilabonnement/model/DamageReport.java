package dk.bilabonnement.bilabonnement.model;

import java.time.LocalDate;

public class DamageReport {
    private Integer reportId;
    private Integer agreementId;
    private LocalDate reportDate;
    private String inspectorName;

    public DamageReport() {
    }

    public DamageReport(Integer reportId, Integer agreementId, LocalDate reportDate, String inspectorName) {
        this.reportId = reportId;
        this.agreementId = agreementId;
        this.reportDate = reportDate;
        this.inspectorName = inspectorName;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Integer agreementId) {
        this.agreementId = agreementId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }
}
