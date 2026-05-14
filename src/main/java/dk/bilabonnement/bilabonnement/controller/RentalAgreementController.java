package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Car;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RentalAgreementController {

    @Autowired
    RentalAgreementRepository rentalAgreementRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/rentals")
    public String showRentals(Model model) {
        List<RentalAgreement> rentalAgreements = rentalAgreementRepository.getAll();

        Map<Integer, String> customerNames = new HashMap<>();
        for (RentalAgreement agreement : rentalAgreements) {
            Customer customer = customerRepository.getById(agreement.getCustomerId());
            customerNames.put(agreement.getCustomerId(), customer.getName());
        }

        Map<Integer, String> carNames = new HashMap<>();
        for (RentalAgreement agreement : rentalAgreements) {
            Car car = carRepository.getById(agreement.getCarId());
            carNames.put(agreement.getCarId(), car.getBrand() + " " + car.getModel());
        }

        model.addAttribute("carNames", carNames);

        model.addAttribute("rentalAgreements", rentalAgreements);
        model.addAttribute("customerNames", customerNames);
        return "rentals";
    }

    @GetMapping("/rentals/new")
    public String showAddRentalForm(Model model){
        model.addAttribute("cars", carRepository.getAvailableCars());
        model.addAttribute("customers", customerRepository.getAll());
        model.addAttribute("rentalAgreement", new RentalAgreement());

        return "add-rentalAgreement";
    }

    @PostMapping("/rentals/save")
    public String saveRentalAgreement(@Valid @ModelAttribute RentalAgreement rentalAgreement, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("cars", carRepository.getAvailableCars());
            model.addAttribute("customers", customerRepository.getAll());
            return "add-rentalAgreement";
        }
        rentalAgreement.setStatus("Active");
        rentalAgreementRepository.save(rentalAgreement);
        carRepository.updateStatus(rentalAgreement.getCarId(), "Rented");
        return "redirect:/rentals";
    }

    @GetMapping("/rentals/{agreementId}/end")
    public String endRental(@PathVariable Integer agreementId){
        RentalAgreement agreement = rentalAgreementRepository.getById(agreementId);
        rentalAgreementRepository.updateStatus(agreementId, "Ended");
        carRepository.updateStatus(agreement.getCarId(), "Awaiting inspection");
        return "redirect:/rentals";
    }
}
