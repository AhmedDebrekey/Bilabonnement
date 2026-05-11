package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Customer;
import dk.bilabonnement.bilabonnement.model.RentalAgreement;
import dk.bilabonnement.bilabonnement.repository.CarRepository;
import dk.bilabonnement.bilabonnement.repository.CustomerRepository;
import dk.bilabonnement.bilabonnement.repository.RentalAgreementRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentalAgreementController {

    @Autowired
    RentalAgreementRepository rentalAgreementRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/rentals")
    public String showRentals(Model model){
        List<RentalAgreement> rentalAgreements = rentalAgreementRepository.getAll();
        model.addAttribute("rentalAgreements", rentalAgreements);
        return "rentals";
    }

    @GetMapping("/rentals/new")
    public String showAddRentalForm(Model model){
        model.addAttribute("cars", carRepository.getAll());
        model.addAttribute("customers", customerRepository.getAll());
        model.addAttribute("rentalAgreement", new RentalAgreement());

        return "add-rentalAgreement";
    }

    @PostMapping("/rentals/save")
    public String saveRentalAgreement(@Valid @ModelAttribute RentalAgreement rentalAgreement, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("cars", carRepository.getAll());
            model.addAttribute("customers", customerRepository.getAll());
            return "add-rentalAgreement";
        }
        rentalAgreementRepository.save(rentalAgreement);
        return "redirect:/rentals";
    }
}
