package com.torrentmedia.torrentmedia.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.torrentmedia.torrentmedia.entity.*;
import com.torrentmedia.torrentmedia.repository.*;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    private NewsLetterRepo newsLetterRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InfluencerRepository influencerRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ContactUs contactUs;

    @Autowired
    InfluencerService influencerService;

    @Autowired
    MailService mailService;



    public void saveNewsLetter(NewsLetter newsLetter){

        newsLetterRepo.save(newsLetter);
        try{
            mailService.sendEmail(newsLetter.getEmail(),"Welcome to Torrent Media Newsletter!","Thank you for subscribing to our newsletter!");
        }catch(Exception e){
            System.out.println("giving Exception");
        }

    }

    public void saveContactUs(ContactUsForm contactUsForm){
        contactUs.save(contactUsForm);
    }


    public void saveNewUser(User user) {
        System.out.println(user.getUserName() + " " + user.getEmail() + "" + user.getPassword());
        userRepository.save(user);
    }

    public String loginAuthentication(String email, String password) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));

        if (user.isPresent()) {
            User user1 = user.get();
            if (user1.getPassword().equals(password)) return "valid";
            else return "invalid";
        } else {
            return "invalid"; // Or "user not found"
        }

    }

    public boolean saveInfluencer(Influencer influencer)  {
            Long Id = influencer.getId();
            influencerRepository.save(influencer);
            System.out.println(influencer);

        return true;
    }

    public boolean save(MultipartFile file, String email) throws IOException {
        Image image1 = new Image();
        image1.setName(file.getOriginalFilename());
        image1.setType(file.getContentType());
        image1.setImage(file.getBytes());
        System.out.println(file.getBytes());
        image1.setRegisterEmailId(email);

        String email1 = image1.getRegisterEmailId();
        Image image = imageRepository.findByRegisterEmailId(email);

        boolean status = getImageByRegisterEmailId(email1);

        if(!status){
            System.out.println(image1.getImage());

            imageRepository.save(image1);
        }else{
            System.out.println("email Already Registered.");
        }

        return true;
    }

    public boolean getImageByRegisterEmailId(String email) {
        Optional<Image> image = Optional.ofNullable(imageRepository.findByRegisterEmailId(email));
        System.out.println(email);
        System.out.println(image);
        if (image.isPresent()) {
            return true;

        }
        return false;
    }
//    public String getBase64ImageByEmail(String email) {
//        Image image = imageRepository.findByRegisterEmailId(email);
//        if (image != null && image.getImage() != null) {
//            return Base64.getEncoder().encodeToString(image.getImage());
//        }
//        return null;
//    }

    public Map<String ,String> getInfluencerDetail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Influencer influencer = influencerService.getInfluencerByEmail(email);
        Map<String,String> influencerDetail = influencerService.getInfluencerDetail(influencer.getId());
        return influencerDetail;
    }
    public Map<String ,String> getInfluencerDetailByEmail(String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
        Influencer influencer = influencerService.getInfluencerByEmail(email);
        Map<String,String> influencerDetail = influencerService.getInfluencerDetail(influencer.getId());
        return influencerDetail;
    }

    public User getUserByEmail(String email){
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        User user1;
        if (user.isPresent()) {
            user1 = user.get();

        } else {
           user1 = null; // Or "user not found"
        }

        return user1;
    }
}
