package com.torrentmedia.torrentmedia.entity;

import jakarta.persistence.*;

@Entity
@Table
public class NewsLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
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
