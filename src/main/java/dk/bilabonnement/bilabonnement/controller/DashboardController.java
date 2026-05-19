package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.repository.CarRepository;
import dk.bilabonnement.bilabonnement.repository.CustomerRepository;
import dk.bilabonnement.bilabonnement.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    RentalAgreementRepository rentalAgreementRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/")
    public String showDashboard(Model model) {
        model.addAttribute("active", rentalAgreementRepository.countActive());
        model.addAttribute("totalRevenue", rentalAgreementRepository.getTotalRevenue());
        model.addAttribute("totalCars", carRepository.countTotalCars());
        model.addAttribute("totalCustomers", customerRepository.countTotalCustomers());
        model.addAttribute("available", carRepository.countByStatus("Available"));
        model.addAttribute("awaitingInspection", carRepository.countByStatus("Awaiting inspection"));
        return "dashboard";
    }

}
