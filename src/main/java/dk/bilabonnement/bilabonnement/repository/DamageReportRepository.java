package dk.bilabonnement.bilabonnement.repository;

import dk.bilabonnement.bilabonnement.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageReportRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DamageRepository damageRepository;

    public List<DamageReport> getAll(){
        return jdbcTemplate.query("SELECT * FROM damage_reports", (rs, rowNum) -> {
            DamageReport damageReport = new DamageReport();
            damageReport.setReportId(rs.getInt("report_id"));
            damageReport.setAgreementId(rs.getInt("agreement_id"));
            damageReport.setReportDate(rs.getDate("report_date").toLocalDate());
            damageReport.setInspectorName(rs.getString("inspector_name"));
            return damageReport;
        });
    }

    public void save(DamageReport damageReport)
    {
        jdbcTemplate.update(
                "INSERT INTO damage_reports (agreement_id, report_date, inspector_name) VALUES (?, ?, ?)",
                damageReport.getAgreementId(),
                damageReport.getReportDate(),
                damageReport.getInspectorName()
        );
    }

    public DamageReport getById(Integer reportId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM damage_reports WHERE report_id = ?",
                (rs, rowNum) -> {
                    DamageReport damageReport = new DamageReport();
                    damageReport.setReportId(rs.getInt("report_id"));
                    damageReport.setAgreementId(rs.getInt("agreement_id"));
                    damageReport.setReportDate(rs.getDate("report_date").toLocalDate());
                    damageReport.setInspectorName(rs.getString("inspector_name"));
                    return damageReport;
                },
                reportId
        );
    }
}
