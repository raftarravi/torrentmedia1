package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.entity.NewsLetter;
import com.torrentmedia.torrentmedia.entity.SubmitContactUs;
import com.torrentmedia.torrentmedia.repository.ContactUs;
import com.torrentmedia.torrentmedia.repository.NewsLetterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private ContactUs contactUs;
    @Autowired
    private NewsLetterRepo newsLetterRepo;



    public String newsletterMail(NewsLetter newsLetter) {

        newsLetterRepo.save(newsLetter);

        return "success"; // Redirect to home with a query param
    }

    public void ContactUsMail(SubmitContactUs contactUs){

    }

    public void getInTouchWithUs(ContactUsForm contactUsForm){

    }
}
