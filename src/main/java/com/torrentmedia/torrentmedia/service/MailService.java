package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import com.torrentmedia.torrentmedia.entity.SubmitContactUs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;


    public String newsletterMail(String userEmail) {


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("doctoappointment14@gmail.com"); // Your inbox
        message.setSubject("New Subscription of Newsletter");
        message.setText("New user subscribed with email: " + userEmail);
       // message.setFrom("torrentmediaa@gmail.com"); // Optional but recommended

        // to the subcribe

        SimpleMailMessage userMessage = new SimpleMailMessage();
        userMessage.setTo(userEmail);
        userMessage.setSubject("confirmation mail");
        userMessage.setText( " welcome to the torrent media you Subscribed our NewsLetter \n Thank You . \n Torrent Media");




        mailSender.send(message);
        mailSender.send(userMessage);

        return "success"; // Redirect to home with a query param
    }

    public void ContactUsMail(SubmitContactUs contactUs){
        SimpleMailMessage userMessage = new SimpleMailMessage();
        userMessage.setTo(contactUs.getEmail()); // Your inbox
        userMessage.setSubject("confirmation mail");
        userMessage.setText("Thank You For Contactin Us \n We will get Back to you Very Shortly.");

        mailSender.send(userMessage);

        SimpleMailMessage adminMessage = new SimpleMailMessage();
        adminMessage.setTo("doctoappointment14@gmail.com");
        adminMessage.setSubject("New User Want to Talk.");
        adminMessage.setText("Details of The User \n" + "UserName : " + contactUs.getFullName() + "\n Email : "+ contactUs.getEmail() + "\n Contact : " + contactUs.getPhoneNumber()+ "\n Message : " + contactUs.getMessage() + "\n"  + " . \n Thank You ");

        mailSender.send(adminMessage);
    }

    public void getInTouchWithUs(ContactUsForm contactUsForm){

        SimpleMailMessage userMessage = new SimpleMailMessage();
        userMessage.setTo(contactUsForm.getEmail()); // Your inbox
        userMessage.setSubject("confirmation mail");
        userMessage.setText("Thank You For Contactin Us \n We will get Back to you Very Shortly.");

        mailSender.send(userMessage);

        SimpleMailMessage adminMessage = new SimpleMailMessage();
        adminMessage.setTo("doctoappointment14@gmail.com");
        adminMessage.setSubject("New User Want to Talk.");
        adminMessage.setText("Details of The User \n" + "UserName : " + contactUsForm.getName() + "\n Email : "+ contactUsForm.getEmail() + "\n Contact : " + contactUsForm.getPhone()+ "\n Message : " + contactUsForm.getSubject() +"\n" + contactUsForm.getMessage() + " . \n Thank You ");

        mailSender.send(adminMessage);

    }
}
