package com.torrentmedia.torrentmedia.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/services")
    public String showServicePage(HttpServletRequest request ,Model model){
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("isLoggedIn" , false);
        return "service1";
    }

    @GetMapping("/services/web-development")
    public String webDevlopmentPage(Model model){
        model.addAttribute("isLoggedIn" , false);
        return "our-services/web-devlopment";
    }

    @GetMapping("services/social-media")
    public String socialMedia(Model model){
        model.addAttribute("isLoggedIn" , false);
        return "our-services/social-media-marketing";
    }

    @GetMapping("services/ads")
    public String advertisment(Model model){
        model.addAttribute("isLoggedIn" , false);
        return "our-services/advertisment";
    }

    @GetMapping("services/branding")
    public String branding(Model model){
        model.addAttribute("isLoggedIn" , false);
        return "our-services/branding";
    }

    @GetMapping("services/video-production")
    public String brandPromotion(Model model){
        model.addAttribute("isLoggedIn" , false);
        return"our-services/brand-promotion.css";
    }

    @GetMapping("services/content-strategy")
    public String contentStratgy(Model model){
        model.addAttribute("isLoggedIn" , false);
        return"our-services/content-strategy";
    }


}
