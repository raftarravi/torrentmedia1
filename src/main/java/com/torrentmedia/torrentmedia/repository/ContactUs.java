package com.torrentmedia.torrentmedia.repository;

import com.torrentmedia.torrentmedia.entity.ContactUsForm;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactUs extends MongoRepository<ContactUsForm,String> {


}
