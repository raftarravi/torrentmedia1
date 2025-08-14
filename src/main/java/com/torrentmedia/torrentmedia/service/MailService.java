package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.entity.NewsLetter;
import com.torrentmedia.torrentmedia.entity.SubmitContactUs;
import com.torrentmedia.torrentmedia.repository.ContactUs;
import com.torrentmedia.torrentmedia.repository.NewsLetterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {


    @Autowired(required=true)
    private JavaMailSender mailSender;

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



    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("contact@torrentmedia.in"); // Titan email
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
