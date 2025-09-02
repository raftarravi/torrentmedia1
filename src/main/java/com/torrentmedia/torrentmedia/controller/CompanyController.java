package com.torrentmedia.torrentmedia.controller;


import com.torrentmedia.torrentmedia.entity.Company;
import com.torrentmedia.torrentmedia.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {


    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Show list + form
    @GetMapping("/companies-list")
    public String showCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        model.addAttribute("company", new Company()); // for form binding
        return "form/companies-list"; // Thymeleaf template
    }
    @GetMapping("/companies-list/admin")
    public String showCompaniesAdmin(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        //model.addAttribute("companies", companyService.getAllCompanies());
        if (!companies.isEmpty()) {
            Company first = companies.getFirst(); // safe
            model.addAttribute("companies", companies);
        } else {
            // handle empty case gracefully
            model.addAttribute("companies", null);
        }
//        List<Company> cc = companyService.getAllCompanies();
//        System.out.println(cc.getFirst());
        model.addAttribute("company", new Company()); // for form binding
        return "form/form-management"; // Thymeleaf template
    }

    // Save (Create or Update)
    @PostMapping("/companies-list/admin")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies-list/admin";
    }

    // Edit (populate form with existing data)
    @GetMapping("/admin/edit/{id}")
    public String editCompany(@PathVariable String id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        model.addAttribute("companies", companyService.getAllCompanies());
        return "form/form-management";
    }

    // Delete
    @GetMapping("/admin/delete/{id}")
    public String deleteCompany(@PathVariable String id) {
        companyService.deleteCompany(id);
        return "redirect:/companies-list/admin";
    }
}

