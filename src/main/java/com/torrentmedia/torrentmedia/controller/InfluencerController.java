package com.torrentmedia.torrentmedia.controller;

import com.torrentmedia.torrentmedia.entity.Image;
import com.torrentmedia.torrentmedia.entity.Influencer;
import com.torrentmedia.torrentmedia.repository.ImageRepository;
import com.torrentmedia.torrentmedia.service.HomeService;
import com.torrentmedia.torrentmedia.service.InfluencerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
public class InfluencerController {

    @Autowired
    InfluencerService influencerService;

    @Autowired
    private HomeService homeService;

    @Autowired
    private ImageRepository imageRepository;



    @GetMapping("/influencer")
    public String showInfluencerPage(HttpServletRequest request ,Model model) {
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("isLoggedIn", true);

//        List<Influencer> influencers = influencerService.getAllInfluencers(); // ✅ Service layer call
//        model.addAttribute("influencers", influencers);

        List<Influencer> influencers = influencerService.getAllInfluencers();

        // Convert to DTOs with base64 image
        List<Map<String, String>> influencerData = new ArrayList<>();

        for (Influencer inf : influencers) {
            Image image = imageRepository.findByRegisterEmailId(inf.getEmail());

            Map<String, String> data = new HashMap<>();
            data.put("name", inf.getName());
            data.put("email", inf.getEmail());
            data.put("bio",inf.getBio());
            data.put("topRated",String.valueOf(inf.isTopRated()));
            data.put("facebook",inf.getFacebook());
            data.put("instagram",inf.getInstagram());
            data.put("tiktok",inf.getTiktok());
            data.put("twitter",inf.getTwitter());
            data.put("youtube",inf.getYoutube());
            data.put("followerCount",String.valueOf(inf.getFollowerCount()));
            data.put("category",inf.getCategory());
            data.put("location" , inf.getLocation());
            data.put("id",String.valueOf(inf.getId()));
            data.put("image", influencerService.getBase64ImageByEmail(inf.getEmail()));
            influencerData.add(data);
        }

        model.addAttribute("influencers", influencerData);

        return "influencer"; // → Maps to src/main/resources/templates/influencer.html
    }

    @PostMapping("/connect")
    public String connect(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("successMessage", "Your Connection is shared Successfully!");
        return "redirect:/influencer";
    }

    @GetMapping("/influencer/{id}")
    public String getInfluencerById(@PathVariable Long id, Model model) {
        Map<String,String> influencer = influencerService.getInfluencerDetail(id);

        if (influencer == null) {
            // Handle "not found" case
            return "error/404"; // or redirect to a not found page
        }

        model.addAttribute("influencer", influencer);
        return "fragments/authentication/profile"; // Thymeleaf template name
    }

    @GetMapping("/influencers/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        return "redirect:/influencer";
    }


}
