package com.torrentmedia.torrentmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {

    @GetMapping("/faq")
    public String fAQPage(){
        return "faq";
    }

}
