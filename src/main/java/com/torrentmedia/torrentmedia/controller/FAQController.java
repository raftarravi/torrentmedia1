package com.torrentmedia.torrentmedia.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {

    @GetMapping("/faq")
    public String fAQPage(HttpServletRequest request, Model model){
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("isLoggedIn" , false);
        return "faq";
    }

}
