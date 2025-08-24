package com.torrentmedia.torrentmedia.entity;
//import jakarta.persistence.*;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//
//@Entity
@Document(collection = "feedback")
public class Feedback {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private String yourName;
    private String companyName;
    private String yourPosition;
    private String serviceType;

    //@Column(length = 1000)
    private String message;

    public Feedback() {
    }

    public Feedback(String yourName, String companyName, String yourPosition, String serviceType, String message) {
        this.yourName = yourName;
        this.companyName = companyName;
        this.yourPosition = yourPosition;
        this.serviceType = serviceType;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getYourPosition() {
        return yourPosition;
    }

    public void setYourPosition(String yourPosition) {
        this.yourPosition = yourPosition;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
