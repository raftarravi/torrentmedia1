package com.torrentmedia.torrentmedia.controller;

import com.torrentmedia.torrentmedia.entity.Influencer;
import com.torrentmedia.torrentmedia.service.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InfluencerController {

    @Autowired
    InfluencerService influencerService;

    @GetMapping("/influencer")
    public String showInfluencerPage(Model model) {
        model.addAttribute("isLoggedIn", false);

        List<Influencer> influencers = influencerService.getAllInfluencers(); // ✅ Service layer call
        model.addAttribute("influencers", influencers);

        return "influencer"; // → Maps to src/main/resources/templates/influencer.html
    }

    @PostMapping("/connect")
    public String connect(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMessage", "Your Connection is shared Successfully!");
        return "redirect:/influencer";
    }


}
