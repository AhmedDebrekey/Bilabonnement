package dk.bilabonnement.bilabonnement.repository;

import dk.bilabonnement.bilabonnement.model.Car;
import dk.bilabonnement.bilabonnement.model.Customer;
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

    public void updateStatus(Integer carId, String status) {
        jdbcTemplate.update(
                "UPDATE cars SET status = ? WHERE car_id = ?",
                status, carId
        );
    }

    public List<Car> getAvailableCars() {
        return jdbcTemplate.query(
                "SELECT * FROM cars WHERE status = 'Available'",
                (rs, rowNum) -> {
                    Car car = new Car();
                    car.setCarId(rs.getInt("car_id"));
                    car.setLicensePlate(rs.getString("license_plate"));
                    car.setChassisNumber(rs.getString("chassis_number"));
                    car.setBrand(rs.getString("brand"));
                    car.setModel(rs.getString("model"));
                    car.setColor(rs.getString("color"));
                    car.setStatus(rs.getString("status"));
                    return car;
                }
        );
    }

    public Integer countTotalCars() {
        return jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM cars",
                Integer.class
        );
    }

    public void delete(Integer carId) {
        jdbcTemplate.update(
                "DELETE FROM cars WHERE car_id = ?",
                carId
        );
    }

    public Car getById(Integer carId) {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM cars WHERE car_id = ?",
                    (rs, rowNum) -> {
                        Car car = new Car();
                        car.setCarId(rs.getInt("car_id"));
                        car.setLicensePlate(rs.getString("license_plate"));
                        car.setChassisNumber(rs.getString("chassis_number"));
                        car.setBrand(rs.getString("brand"));
                        car.setModel(rs.getString("model"));
                        car.setColor(rs.getString("color"));
                        car.setStatus(rs.getString("status"));
                        return car;
                    },
                    carId
            );
    }
}
