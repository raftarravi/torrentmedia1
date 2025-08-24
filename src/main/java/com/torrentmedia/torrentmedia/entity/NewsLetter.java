package com.torrentmedia.torrentmedia.entity;

//import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//
//@Entity
//@Table
@Document(collection = "newsletter")
public class NewsLetter {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String Id;
    String email;

    public NewsLetter() {
    }

    public NewsLetter(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
