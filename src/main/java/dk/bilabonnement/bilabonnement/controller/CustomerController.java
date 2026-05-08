package dk.bilabonnement.bilabonnement.controller;

import dk.bilabonnement.bilabonnement.model.Customer;
import dk.bilabonnement.bilabonnement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public String showCustomers(Model model){
        List<Customer> customers = customerRepository.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/new")
    public String showAddCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "add-customer";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute Customer customer){
        customerRepository.save(customer);
        return "redirect:/customers";
    }
}
