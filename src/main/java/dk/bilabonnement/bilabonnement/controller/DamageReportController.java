package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Damage;
import dk.bilabonnement.bilabonnement.model.DamageReport;
import dk.bilabonnement.bilabonnement.repository.DamageReportRepository;
import dk.bilabonnement.bilabonnement.repository.DamageRepository;
import dk.bilabonnement.bilabonnement.repository.RentalAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class DamageReportController {
    @Autowired
    DamageReportRepository damageReportRepository;
    @Autowired
    RentalAgreementRepository rentalAgreementRepository;
    @Autowired
    private DamageRepository damageRepository;

    @GetMapping("/damage-reports")
    public String showDamageReports(Model model){
        List<DamageReport> damageReports = damageReportRepository.getAll();
        model.addAttribute("damageReports", damageReports);
        return "damageReports";
    }

    @GetMapping("/damage-reports/new")
    public String showAddDamageReportForm(Model model){
        model.addAttribute("endedAgreements", rentalAgreementRepository.getEndedAgreements());
        model.addAttribute("damageReport", new DamageReport());
        return "add-damage-report";
    }

    @PostMapping("/damage-reports/save")
    public String saveDamageReport(@ModelAttribute DamageReport damageReport){
        damageReportRepository.save(damageReport);
        return "redirect:/damage-reports";
    }

    @GetMapping("damage-reports/{reportId}")
    public String showDamageReport(@PathVariable int reportId, Model model){
        DamageReport damageReport = damageReportRepository.getById(reportId);
        List<Damage> damages = damageRepository.findByReportId(reportId);

        BigDecimal total = damages.stream().map(Damage::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("report", damageReport);
        model.addAttribute("damages", damages);
        model.addAttribute("total", total);

        return "damage-report-detail";
    }
}
