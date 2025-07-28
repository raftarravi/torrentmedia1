package com.torrentmedia.torrentmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {

    @GetMapping("/faq")
    public String fAQPage(Model model){

        model.addAttribute("isLoggedIn" , false);
        return "faq";
    }

}
