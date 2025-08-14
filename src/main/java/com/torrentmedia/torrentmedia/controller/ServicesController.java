package com.torrentmedia.torrentmedia.controller;

import com.torrentmedia.torrentmedia.entity.Feedback;
import com.torrentmedia.torrentmedia.repository.FeedbackRepository;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class ServicesController {

    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/services")
    public String showServicePage(HttpServletRequest request ,Model model){
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("isLoggedIn" , false);
        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Digital Marketing");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Digital Marketing");

        return "service1";
    }

    @GetMapping("/services/web-development")
    public String webDevlopmentPage(Model model){
        model.addAttribute("isLoggedIn" , false);

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Web Development");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Web Development");

        return "our-services/web-devlopment";
    }

    @GetMapping("services/social-media")
    public String socialMedia(Model model){
        model.addAttribute("isLoggedIn" , false);

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Digital Marketing");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Digital Marketing");

        return "our-services/social-media-marketing";
    }

    @GetMapping("services/ads")
    public String advertisment(Model model){
        model.addAttribute("isLoggedIn" , false);

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Paid Advertisement");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Paid Advertisement");

        return "our-services/advertisment";
    }

    @GetMapping("services/branding")
    public String branding(Model model){
        model.addAttribute("isLoggedIn" , false);
        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Graphic Design And UI/UX Design");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Graphic Design And UI/UX Design");
        return "our-services/branding";
    }

    @GetMapping("services/video-production")
    public String brandPromotion(Model model){
        model.addAttribute("isLoggedIn" , false);
        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Video Editing And Content Shoot");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Video Editing And Content Shoot");

        return"our-services/brand-promotion";
    }

    @GetMapping("services/content-strategy")
    public String contentStratgy(Model model){
        model.addAttribute("isLoggedIn" , false);

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Content Strategy");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Content Strategy");
        return"our-services/content-strategy";
    }


}
