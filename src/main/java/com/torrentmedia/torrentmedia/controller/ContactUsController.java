package com.torrentmedia.torrentmedia.controller;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.service.ContactUsService;
import com.torrentmedia.torrentmedia.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactUsController {


    @Autowired
    private MailService mailService ;
    @Autowired
    private ContactUsService contactUsService;


    @GetMapping("/contact")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactUsForm()); // Replace with your actual class
        return "contact-us"; // Your Thymeleaf view name
    }


    @PostMapping("/submitContact")
    public String submitContact(ContactUsForm contactUsForm, Model model , RedirectAttributes redirectAttributes){
        contactUsService.saveContactUs(contactUsForm);
        redirectAttributes.addFlashAttribute("successMessage", "Thank you for contacting we will get back soon!");
        //System.out.println(contact);
        return "redirect:/contact";
    }
}
