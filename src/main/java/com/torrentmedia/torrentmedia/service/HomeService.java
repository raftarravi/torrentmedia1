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
    public String getBase64ImageByEmail(String email) {
        Image image = imageRepository.findByRegisterEmailId(email);
        if (image != null && image.getImage() != null) {
            return Base64.getEncoder().encodeToString(image.getImage());
        }
        return null;
    }
}
