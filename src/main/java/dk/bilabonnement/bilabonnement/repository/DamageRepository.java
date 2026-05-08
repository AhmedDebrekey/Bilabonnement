package dk.bilabonnement.bilabonnement.repository;

import dk.bilabonnement.bilabonnement.model.Damage;
import dk.bilabonnement.bilabonnement.model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Damage> findByReportId(int reportId) {
        return jdbcTemplate.query(
                "SELECT * FROM damages WHERE report_id = ?",
                (rs, rowNum) -> {
                    Damage damage = new Damage();
                    damage.setDamageId(rs.getInt("damage_id"));
                    damage.setReportId(rs.getInt("report_id"));
                    damage.setDescription(rs.getString("description"));
                    damage.setPrice(rs.getBigDecimal("price"));
                    return damage;
                },
                reportId
        );
    }

    public void save(Damage damage){
        jdbcTemplate.update(
                "INSERT INTO damages (report_id, description, price) VALUES (?, ?, ?)",
                damage.getReportId(),
                damage.getDescription(),
                damage.getPrice()
        );
    }
}
