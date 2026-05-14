package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Car;
import dk.bilabonnement.bilabonnement.repository.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.HandlerMapping;

import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private HandlerMapping resourceHandlerMapping;

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
    public String saveCar(@Valid @ModelAttribute Car car, BindingResult result){
        if (result.hasErrors()) {
            return "add-car";
        }
        carRepository.save(car);
        return "redirect:/cars";
    }

    @GetMapping("/cars/delete/{carId}")
    public String deleteCar(@PathVariable Integer carId) {
        carRepository.delete(carId);
        return "redirect:/cars";
    }
}
