package com.torrentmedia.torrentmedia.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.torrentmedia.torrentmedia.entity.*;
import com.torrentmedia.torrentmedia.repository.FeedbackRepository;
import com.torrentmedia.torrentmedia.repository.ImageRepository;
import com.torrentmedia.torrentmedia.service.HomeService;
import com.torrentmedia.torrentmedia.service.MailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private MailService mailService;
    @Autowired
    private HomeService homeService;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;



    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        model.addAttribute("currentURI", request.getRequestURI());
        model.addAttribute("isLoggedIn" , false);
        model.addAttribute("feedback", new Feedback());

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Digital Marketing");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Digital Marketing");

        return "home";
    }


    @PostMapping("/subscribe")
    public String handleSubscription(@RequestParam("email") String email, RedirectAttributes redirectAttributes ,Model model) {

        System.out.println("Subscribed Email: " + email);
        NewsLetter newsLetter = new NewsLetter();
        newsLetter.setEmail(email);
        homeService.saveNewsLetter(newsLetter);

        model.addAttribute("isLoggedIn" , false);
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
    , RedirectAttributes redirectAttributes , Model model) {
        ContactUsForm contactUsForm = new ContactUsForm(fullName,email,message,phoneNumber);
        homeService.saveContactUs(contactUsForm);


        System.out.println("Contact from: " + fullName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Message: " + message);

        model.addAttribute("isLoggedIn" , false);
        model.addAttribute("feedback", new Feedback());
        // Optionally send email or store in DB
        redirectAttributes.addFlashAttribute("successMessage", "Thank you for contacting us!");
        return "redirect:/"; // Redirect with success param
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        boolean isLoggedIn = false; // or get it from session, manually
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("feedback", new Feedback());

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Web Development");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Web Development");

        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model ,Authentication authentication){

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/profile";
        }
        model.addAttribute("isLoggedIn" , false);
        return "fragments/authentication/login";

    }


    @PostMapping("/process-login")
    public String loginAuthentication(@RequestParam String email,
                                      @RequestParam String password,
                                      Model model) {

        // 1️⃣ Authenticate user
        String status = homeService.loginAuthentication(email, password);

        if (!"valid".equals(status)) {
            model.addAttribute("loginError", "Invalid email or password");
            return "fragments/authentication/login";
        }

        // 2️⃣ Get the user from DB
        User user = homeService.getUserByEmail(email);
        if (user == null) {
            model.addAttribute("loginError", "You are not registered yet");
            return "fragments/authentication/login";
        }

        model.addAttribute("isLoggedIn", true);

        // 3️⃣ Get influencer details for this user
        Map<String, String> influencer = homeService.getInfluencerDetailByEmail(user.getEmail());

        // 4️⃣ If influencer exists → show profile, else → show home
        if (influencer != null && !influencer.isEmpty()) {
            model.addAttribute("influencer", influencer);
            return "fragments/authentication/profile";
        } else {
            model.addAttribute("feedback", new Feedback());
            List<Feedback> reviews = feedbackRepository.findAllByServiceType("Graphic Design And UI/UX Design");
            model.addAttribute("reviews", reviews);
            model.addAttribute("serviceType", "Graphic Design And UI/UX Design");
            return "home";
        }
    }


    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("isLoggedIn" , false);
        return "fragments/authentication/sign-up";
    }

    @PostMapping("/signup")
    public String userRegistration( @RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String password,Model model) {
        System.out.println(name + " " + email + "" + password);
        User users = homeService.getUserByEmail(email);
        if(users != null){
            model.addAttribute("loginError" , "email is already in use so use other email");
            return "fragments/authentication/sign-up";
        }
        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        homeService.saveNewUser(user);
        model.addAttribute("isLoggedIn" , false);

        return "fragments/authentication/login";
    }





    @GetMapping("/help")
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactUsForm());
        model.addAttribute("isLoggedIn" , false);
        return "contact-us";
    }

    @GetMapping("logout")
    public String logOut(Model model){
        model.addAttribute("isLoggedIn" , false);
        model.addAttribute("feedback", new Feedback());

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Digital Marketing");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Digital Marketing");


        return "home";
    }

    @GetMapping("/profile")
    public String profile(Model model , Authentication authentication){

        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("isLoggedIn", true);
            Map<String,String> influencer = homeService.getInfluencerDetail();

            if (influencer == null) {
                // Handle "not found" case
                return "error/404"; // or redirect to a not found page
            }

            model.addAttribute("influencer", influencer);


        } else {
            return "redirect:/login";
        }


        return "fragments/authentication/profile";
    }
    @GetMapping("/settings")
    public String setting(Model model){
        model.addAttribute("isLoggedIn" , true);
        model.addAttribute("feedback", new Feedback());

        List<Feedback> reviews = feedbackRepository.findAllByServiceType("Digital Marketing");
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", "Digital Marketing");

        return "home";
    }


    @GetMapping("/signup-as-influencer")
    public String signUpAsInfluencer(){

        return "fragments/authentication/influencer-registration";
    }

    @PostMapping("/register-as-influencer")
    public String registerInfluencer(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("gender") String gender,
            @RequestParam("category") String category,
            @RequestParam("followerCount") Long followerCount,
            @RequestParam("location") String location,
            @RequestParam("bio") String bio,
            @RequestParam(value = "topRated", required = false, defaultValue = "false") boolean topRated,
            @RequestParam("facebook") String facebook,
            @RequestParam("instagram") String instagram,
            @RequestParam("youtube") String youtube,
            @RequestParam("twitter") String twitter,
            @RequestParam("tiktok") String tiktok,
            HttpSession session,
            Model model
    ) {
        User exitinfluencer = homeService.getUserByEmail(email);
        if(exitinfluencer != null){
            model.addAttribute("loginError" ,"your email is already in use");
            return "fragments/authentication/influencer-registration";
        }

        // Validate password confirmation
        if (!password.equals(confirmPassword)) {
            // Redirect with error message (optional)
            return "redirect:/register?error=password-mismatch";
        }

        // Build Influencer object
        Influencer influencer = new Influencer();
        influencer.setName(name);
        influencer.setEmail(email);
        influencer.setPassword(password);
        influencer.setGender(gender);
        influencer.setCategory(category);
        influencer.setFollowerCount(followerCount);
        influencer.setLocation(location);
        influencer.setBio(bio);
        influencer.setTopRated(topRated);
        influencer.setFacebook(facebook);
        influencer.setInstagram(instagram);
        influencer.setYoutube(youtube);
        influencer.setTwitter(twitter);
        influencer.setTiktok(tiktok);

        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);

        homeService.saveNewUser(user);

        // Save to database
        boolean status = homeService.saveInfluencer(influencer);
        session.setAttribute("influencerId" ,status);

        // You can use RedirectAttributes to pass success/failure message
        return "fragments/authentication/image"; // or return to a success page
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("email") String email) throws IOException {

        boolean home = homeService.save(file,email);
        return "redirect:/influencer";
    }

    @GetMapping("/image/{email}")
    public void serveImageByEmail(@PathVariable String email, HttpServletResponse response) {
        Image image = imageRepository.findByRegisterEmailId(email);

        if (image != null && image.getImage() != null) {
            try {
                response.setContentType(image.getType()); // e.g. image/jpeg
                response.setContentLength(image.getImage().length);

                OutputStream os = response.getOutputStream();
                os.write(image.getImage());
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @GetMapping("/error")
    public String showCustomError(@RequestParam(value = "message", required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "error"; // will load error.html from templates
    }


    @PostMapping("/submit-feedback")
    public String submitFeedback(@ModelAttribute Feedback feedback, Model model) {
        System.out.println(feedback.getCompanyName() + " " + feedback.getMessage());
        feedbackRepository.save(feedback);
        model.addAttribute("Message", "Thank you for your feedback!");
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("isLoggedIn" , true);

        List<Feedback> reviews = feedbackRepository.findAllByServiceType(feedback.getServiceType());
        model.addAttribute("reviews", reviews);
        model.addAttribute("serviceType", feedback.getServiceType());


        return "home"; // redirect to home or any page
    }



}
