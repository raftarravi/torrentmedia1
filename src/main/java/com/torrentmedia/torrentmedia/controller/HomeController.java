package com.torrentmedia.torrentmedia.controller;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.entity.NewsLetter;
import com.torrentmedia.torrentmedia.entity.SubmitContactUs;
import com.torrentmedia.torrentmedia.service.HomeService;
import com.torrentmedia.torrentmedia.service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private MailService mailService;
    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        model.addAttribute("currentURI", request.getRequestURI());
        return "home";
    }


    @PostMapping("/subscribe")
    public String handleSubscription(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {

        System.out.println("Subscribed Email: " + email);
        NewsLetter newsLetter = new NewsLetter();
        newsLetter.setEmail(email);
        homeService.saveNewsLetter(newsLetter);


        redirectAttributes.addFlashAttribute("successMessage", "Thank you for subscribing!");
        //mailService.newsletterMail();

        return "redirect:/";
    }



    @PostMapping("/submitContactUs")
    public String handleContactUs(
            @RequestParam String fullName,
            @RequestParam String phoneNumber,
            @RequestParam String email,
            @RequestParam String message
    , RedirectAttributes redirectAttributes) {
        ContactUsForm contactUsForm = new ContactUsForm(fullName,email,message,phoneNumber);
        homeService.saveContactUs(contactUsForm);


        System.out.println("Contact from: " + fullName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        // Optionally send email or store in DB
        redirectAttributes.addFlashAttribute("successMessage", "Thank you for contacting us!");
        return "redirect:/"; // Redirect with success param
    }

}
