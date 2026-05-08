package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    RentalAgreementRepository rentalAgreementRepository;

    @GetMapping("/")
    public String showDashboard(Model model)
    {
        model.addAttribute("active", rentalAgreementRepository.countActive());
        model.addAttribute("totalRevenue", rentalAgreementRepository.getTotalRevenue());
        return "dashboard";
    }

}
