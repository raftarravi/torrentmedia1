package com.torrentmedia.torrentmedia.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "image") //, columnDefinition = "LONGBLOB" for my sql .
    private byte[] image;


    String registerEmailId;

    public String getRegisterEmailId() {
        return registerEmailId;
    }

    public void setRegisterEmailId(String registerEmailId) {
        this.registerEmailId = registerEmailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

