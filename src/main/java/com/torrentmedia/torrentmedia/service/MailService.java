package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.entity.NewsLetter;
import com.torrentmedia.torrentmedia.entity.SubmitContactUs;
import com.torrentmedia.torrentmedia.repository.ContactUs;
import com.torrentmedia.torrentmedia.repository.NewsLetterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private ContactUs contactUs;
    @Autowired
    private NewsLetterRepo newsLetterRepo;
    @Autowired
    private JavaMailSender mailSender;


    public String newsletterMail(NewsLetter newsLetter) {

        newsLetterRepo.save(newsLetter);

        return "success"; // Redirect to home with a query param
    }

    public void ContactUsMail(SubmitContactUs contactUs){

    }

    public void getInTouchWithUs(ContactUsForm contactUsForm){

//        SimpleMailMessage userMessage = new SimpleMailMessage();
//        userMessage.setTo(contactUsForm.getEmail()); // Your inbox
//        userMessage.setSubject("confirmation mail");
//        userMessage.setText("Thank You For Contactin Us \n We will get Back to you Very Shortly.");
//
//        mailSender.send(userMessage);
//
//        SimpleMailMessage adminMessage = new SimpleMailMessage();
//        adminMessage.setTo("doctoappointment14@gmail.com");
//        adminMessage.setSubject("New User Want to Talk.");
//        adminMessage.setText("Details of The User \n" + "UserName : " + contactUsForm.getName() + "\n Email : "+ contactUsForm.getEmail() + "\n Contact : " + contactUsForm.getPhone()+ "\n Message : " + contactUsForm.getSubject() +"\n" + contactUsForm.getMessage() + " . \n Thank You ");
//
//        mailSender.send(adminMessage);


    }
}
