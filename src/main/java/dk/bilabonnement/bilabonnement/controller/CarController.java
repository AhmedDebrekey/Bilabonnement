package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Car;
import dk.bilabonnement.bilabonnement.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public String showCars(Model model){
        List<Car> cars = carRepository.getAll();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/cars/new")
    public String showAddCarForm(Model model){
        model.addAttribute("car", new Car());
        return "add-car";
    }

    @PostMapping("/cars/save")
    public String saveCar(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/cars";
    }
}
