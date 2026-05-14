package dk.bilabonnement.bilabonnement.repository;

import dk.bilabonnement.bilabonnement.model.DamageReport;
import dk.bilabonnement.bilabonnement.model.RentalAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RentalAgreementRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RentalAgreement> getAll(){
        return jdbcTemplate.query("SELECT * FROM rental_agreements", (rs, rowNum) -> {
            RentalAgreement rentalAgreement = new RentalAgreement();
            rentalAgreement.setAgreementId(rs.getInt("agreement_id"));
            rentalAgreement.setCarId(rs.getInt("car_id"));
            rentalAgreement.setCustomerId(rs.getInt("customer_id"));
            rentalAgreement.setStartDate(rs.getDate("start_date").toLocalDate());
            rentalAgreement.setEndDate(rs.getDate("end_date").toLocalDate());
            rentalAgreement.setMonthlyPrice(rs.getBigDecimal("monthly_price"));
            rentalAgreement.setPickupLocation(rs.getString("pickup_location"));
            rentalAgreement.setStatus(rs.getString("status"));
            return rentalAgreement;
        });
    }

    public void save(RentalAgreement rentalAgreement){
        jdbcTemplate.update(
                "INSERT INTO rental_agreements (car_id, customer_id, start_date, end_date, monthly_price, pickup_location, status) VALUES (?, ?, ?, ?, ?, ?, ?)",
                rentalAgreement.getCarId(),
                rentalAgreement.getCustomerId(),
                rentalAgreement.getStartDate(),
                rentalAgreement.getEndDate(),
                rentalAgreement.getMonthlyPrice(),
                rentalAgreement.getPickupLocation(),
                rentalAgreement.getStatus()
        );
    }

    public List<RentalAgreement> getEndedAgreements() {
        return jdbcTemplate.query(
                "SELECT * FROM rental_agreements WHERE status = ?",
                (rs, rowNum) -> {
                    RentalAgreement rentalAgreement = new RentalAgreement();
                    rentalAgreement.setAgreementId(rs.getInt("agreement_id"));
                    rentalAgreement.setCarId(rs.getInt("car_id"));
                    rentalAgreement.setCustomerId(rs.getInt("customer_id"));
                    rentalAgreement.setStartDate(rs.getDate("start_date").toLocalDate());
                    rentalAgreement.setEndDate(rs.getDate("end_date").toLocalDate());
                    rentalAgreement.setMonthlyPrice(rs.getBigDecimal("monthly_price"));
                    rentalAgreement.setPickupLocation(rs.getString("pickup_location"));
                    rentalAgreement.setStatus(rs.getString("status"));
                    return rentalAgreement;
                },
                "Ended"
        );
    }

    public Integer countActive(){
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM rental_agreements WHERE status = 'Active'", Integer.class);
    }

    public BigDecimal getTotalRevenue(){
        return jdbcTemplate.queryForObject("SELECT SUM(monthly_price) FROM rental_agreements WHERE status = 'Active'", BigDecimal.class);
    }

    public RentalAgreement getById(Integer agreementId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM rental_agreements WHERE agreement_id = ?",
                (rs, rowNum) -> {
                    RentalAgreement rentalAgreement = new RentalAgreement();
                    rentalAgreement.setAgreementId(rs.getInt("agreement_id"));
                    rentalAgreement.setCarId(rs.getInt("car_id"));
                    rentalAgreement.setCustomerId(rs.getInt("customer_id"));
                    rentalAgreement.setStartDate(rs.getDate("start_date").toLocalDate());
                    rentalAgreement.setEndDate(rs.getDate("end_date").toLocalDate());
                    rentalAgreement.setMonthlyPrice(rs.getBigDecimal("monthly_price"));
                    rentalAgreement.setPickupLocation(rs.getString("pickup_location"));
                    rentalAgreement.setStatus(rs.getString("status"));
                    return rentalAgreement;
                },
                agreementId
        );
    }

    public void updateStatus(Integer agreementId, String status){
        jdbcTemplate.update(
                "UPDATE rental_agreements SET status = ? WHERE agreement_id = ?",
                status, agreementId
        );
    }
}
