package dk.bilabonnement.bilabonnement.repository;

import dk.bilabonnement.bilabonnement.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Car> getAll(){
        return jdbcTemplate.query("SELECT * FROM cars", (rs, rowNum) -> {
            Car car = new Car();
            car.setCarId(rs.getInt("car_id"));
            car.setLicensePlate(rs.getString("license_plate"));
            car.setBrand(rs.getString("brand"));
            car.setColor(rs.getString("color"));
            car.setModel(rs.getString("model"));
            car.setStatus(rs.getString("status"));
            car.setChassisNumber(rs.getString("chassis_number"));
            return car;
        });
    }

    public void save(Car car) {
        jdbcTemplate.update(
                "INSERT INTO cars (license_plate, chassis_number, brand, model, color, status) VALUES (?, ?, ?, ?, ?, ?)",
                car.getLicensePlate(),
                car.getChassisNumber(),
                car.getBrand(),
                car.getModel(),
                car.getColor(),
                car.getStatus()
        );
    }
}
