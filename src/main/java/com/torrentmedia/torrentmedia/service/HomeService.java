package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.entity.NewsLetter;
import com.torrentmedia.torrentmedia.repository.ContactUs;
import com.torrentmedia.torrentmedia.repository.NewsLetterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private NewsLetterRepo newsLetterRepo;

    @Autowired
    private ContactUs contactUs;

    public void saveNewsLetter(NewsLetter newsLetter){
        newsLetterRepo.save(newsLetter);
    }

    public void saveContactUs(ContactUsForm contactUsForm){
        contactUs.save(contactUsForm);
    }




}
