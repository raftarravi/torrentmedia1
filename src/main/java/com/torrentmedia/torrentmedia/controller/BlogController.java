package com.torrentmedia.torrentmedia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/influencer")
    public String showBlogPage(){
        return "blog";
    }

}
