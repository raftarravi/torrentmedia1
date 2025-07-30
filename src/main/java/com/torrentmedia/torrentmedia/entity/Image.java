package com.torrentmedia.torrentmedia.entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Image {

    @Id
    private String id;

    private String name;

    private String type;
    //    @Lob
//    @Basic(fetch = FetchType.EAGER)
//    @Column(name = "image", columnDefinition = "BYTEA") //, columnDefinition = "BYTEA" for mysql .
    private byte[] image;


    String registerEmailId;

    public String getRegisterEmailId() {
        return registerEmailId;
    }

    public void setRegisterEmailId(String registerEmailId) {
        this.registerEmailId = registerEmailId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
