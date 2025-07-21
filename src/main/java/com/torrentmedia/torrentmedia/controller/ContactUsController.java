package com.torrentmedia.torrentmedia.controller;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactUsController {


    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactUsForm()); // Replace with your actual class
        return "contact-us"; // Your Thymeleaf view name
    }


    @PostMapping("/submitContact")
    public String submitContact(ContactUsForm contact , Model model){
        model.addAttribute("contactForm", new ContactUsForm());
        return "contact-us";
    }
}
