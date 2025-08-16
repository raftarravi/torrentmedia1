package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.Image;
import com.torrentmedia.torrentmedia.entity.Influencer;
import com.torrentmedia.torrentmedia.repository.ImageRepository;
import com.torrentmedia.torrentmedia.repository.InfluencerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InfluencerService {

    @Autowired
    InfluencerRepository influencerRepository;

    @Autowired
    ImageRepository imageRepository;


    public List<Influencer> getAllInfluencers() {
        return influencerRepository.findAll();

    }

    public List<Image> getAllImage(){
        return imageRepository.findAll();
    }

    public Influencer getInfluencerById(Long Id){
       return influencerRepository.findById(Id).get();
    }

    public  Map<String, String> getInfluencerDetail(Long Id){

        Influencer inf = getInfluencerById(Id);
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
        data.put("gender",inf.getGender());
        data.put("location" , inf.getLocation());
        data.put("additionalInfo",inf.getBio());
        data.put("id",String.valueOf(inf.getId()));
        data.put("image", getBase64ImageByEmail(inf.getEmail()));

        return data;

    }

   public Influencer getInfluencerByEmail(String email){

        return influencerRepository.findByEmail(email);
    }

    public String getBase64ImageByEmail(String email ) {
        Image image = imageRepository.findByRegisterEmailId(email);
//        Optional<Image> foundImage = images.stream()
//                .filter(img -> img.getRegisterEmailId().equalsIgnoreCase(email))
//                .findFirst();
        if (image != null && image.getImage() != null) {
            return Base64.getEncoder().encodeToString(image.getImage());
        }
        return null;
    }



}
