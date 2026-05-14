package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.*;
import dk.bilabonnement.bilabonnement.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DamageReportController {
    @Autowired
    DamageReportRepository damageReportRepository;
    @Autowired
    RentalAgreementRepository rentalAgreementRepository;
    @Autowired
    DamageRepository damageRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/damage-reports")
    public String showDamageReports(Model model) {
        List<RentalAgreement> endedAgreements = rentalAgreementRepository.getEndedAgreements();

        Map<Integer, DamageReport> reportMap = new HashMap<>();
        Map<Integer, Car> carMap = new HashMap<>();
        Map<Integer, Customer> customerMap = new HashMap<>();

        for (RentalAgreement agreement : endedAgreements) {
            DamageReport report = damageReportRepository.getByAgreementId(agreement.getAgreementId());
            reportMap.put(agreement.getAgreementId(), report);
            carMap.put(agreement.getCarId(), carRepository.getById(agreement.getCarId()));
            customerMap.put(agreement.getCustomerId(), customerRepository.getById(agreement.getCustomerId()));
        }

        model.addAttribute("endedAgreements", endedAgreements);
        model.addAttribute("reportMap", reportMap);
        model.addAttribute("carMap", carMap);
        model.addAttribute("customerMap", customerMap);

        return "damageReports";
    }

    @GetMapping("/damage-reports/new/{agreementId}")
    public String showAddDamageReportForm(@PathVariable Integer agreementId, Model model) {
        DamageReport existing = damageReportRepository.getByAgreementId(agreementId);
        if (existing != null) {
            return "redirect:/damage-reports";
        }
        DamageReport damageReport = new DamageReport();
        damageReport.setAgreementId(agreementId);
        model.addAttribute("damageReport", damageReport);
        return "add-damage-report";
    }

    @PostMapping("/damage-reports/save")
    public String saveDamageReport(@Valid @ModelAttribute DamageReport damageReport, BindingResult result, Model model) {
        System.out.println("Agreement ID received: " + damageReport.getAgreementId());
        System.out.println("Has errors: " + result.hasErrors());
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println("Error: " + error.getDefaultMessage()));
            model.addAttribute("damageReport", damageReport);
            return "add-damage-report";
        }
        damageReportRepository.save(damageReport);
        RentalAgreement agreement = rentalAgreementRepository.getById(damageReport.getAgreementId());
        carRepository.updateStatus(agreement.getCarId(), "Available");
        return "redirect:/damage-reports";
    }

    @GetMapping("/damage-reports/{reportId}")
    public String showDamageReport(@PathVariable int reportId, Model model) {
        DamageReport damageReport = damageReportRepository.getById(reportId);
        List<Damage> damages = damageRepository.findByReportId(reportId);
        BigDecimal total = damages.stream().map(Damage::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        RentalAgreement agreement = rentalAgreementRepository.getById(damageReport.getAgreementId());
        Car car = carRepository.getById(agreement.getCarId());
        Customer customer = customerRepository.getById(agreement.getCustomerId());

        model.addAttribute("report", damageReport);
        model.addAttribute("damages", damages);
        model.addAttribute("total", total);
        model.addAttribute("car", car);
        model.addAttribute("customer", customer);

        return "damage-report-detail";
    }

}
