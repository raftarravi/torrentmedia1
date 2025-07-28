package com.torrentmedia.torrentmedia.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.torrentmedia.torrentmedia.entity.*;
import com.torrentmedia.torrentmedia.repository.*;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
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



    public void saveNewsLetter(NewsLetter newsLetter){
        newsLetterRepo.save(newsLetter);
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
        User user1 = user.get();

        if(user1.getPassword().equals(password)) return "valid";
        else return "invalid";
    }

    public boolean saveInfluencer(Influencer influencer)  {
            Long Id = influencer.getId();
            influencerRepository.save(influencer);
            System.out.println(influencer);

        return true;
    }

    public boolean save(Image image) {
        String email = image.getRegisterEmailId();
        Image image1 = imageRepository.findByRegisterEmailId(email);
        if(image1 == null){
            imageRepository.save(image);
        }else{
            System.out.println("email Already Registered.");
        }

        return true;
    }

    public Image getImageByRegisterEmailId(String email) {
        Optional<Image> image = Optional.ofNullable(imageRepository.findByRegisterEmailId(email));
        System.out.println(email);
        System.out.println(image);
        Image image1= image.get();


        return image1;
    }
    public String getBase64ImageByEmail(String email) {
        Image image = getImageByRegisterEmailId(email);
        if (image != null && image.getImage() != null) {
            return Base64.getEncoder().encodeToString(image.getImage());
        }
        return null;
    }
}
