package dk.bilabonnement.bilabonnement;

import dk.bilabonnement.bilabonnement.model.Car;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class CarValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPass() {
        Car car = new Car();
        car.setLicensePlate("AB12345");
        car.setChassisNumber("WBA3A5C50DF596843");
        car.setBrand("Toyota");
        car.setModel("Yaris");
        car.setColor("White");
        car.setStatus("Available");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertTrue(violations.isEmpty(), "Passed Happy Flow");
    }

    @Test
    void shouldFail() {
        Car car = new Car();
        car.setLicensePlate("");
        car.setChassisNumber("");
        car.setBrand("");
        car.setModel("");
        car.setColor("");
        car.setStatus("");

        Set<ConstraintViolation<Car>> violations = validator.validate(car);

        assertFalse(violations.isEmpty(), "Passed Exception Flow");
    }
}
