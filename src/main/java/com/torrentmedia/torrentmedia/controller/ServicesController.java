package com.torrentmedia.torrentmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/services")
    public String showServicePage(Model model){
        model.addAttribute("isLoggedIn" , false);
        return "service";
    }
}
