package com.torrentmedia.torrentmedia.entity;

public class SubmitContactUs {

        private String fullName;
        private String phoneNumber;
        private String email;
        private String message;




    public SubmitContactUs() {
    }

    public SubmitContactUs(String fullName, String phoneNumber, String email, String message) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.message = message;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
