package com.torrentmedia.torrentmedia.entity;

//import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//
//@Entity
//@Table( name = "users")
@Document(collection = "users")
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    String Id;

    String userName;

    String email;
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
