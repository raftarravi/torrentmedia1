package com.torrentmedia.torrentmedia.service;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactUsService {

    @Autowired
    private  HomeService homeService;
    public void saveContactUs(ContactUsForm contactUsForm){
        homeService.saveContactUs(contactUsForm);
    }



}
