package com.torrentmedia.torrentmedia.entity;

public class ContactUsForm {

    String name;
    String email;
    String Subject;
    String phone;
    String message;


    public ContactUsForm() {
    }

    public ContactUsForm(String name, String email, String subject, String phone, String message) {
        this.name = name;
        this.email = email;
        Subject = subject;
        this.phone = phone;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
