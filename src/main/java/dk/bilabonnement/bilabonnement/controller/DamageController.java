package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Damage;
import dk.bilabonnement.bilabonnement.repository.DamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DamageController {
    @Autowired
    DamageRepository damageRepository;

    @GetMapping("/damages/new/{reportId}")
    public String showAddDamageForm(@PathVariable int reportId, Model model) {
        Damage damage = new Damage();
        damage.setReportId(reportId);
        model.addAttribute("damage", damage);
        return "add-damage";
    }

    @PostMapping("/damages/save")
    public String saveDamage(@ModelAttribute Damage damage){
        damageRepository.save(damage);
        return "redirect:/damage-reports";
    }

}
