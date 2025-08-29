package com.torrentmedia.torrentmedia.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
public class Company {

    @Id
    private String id;
    private String name;
    private String description;
    private String formLink;

    // Constructors
    public Company() {}

    public Company(String name, String description, String formLink) {
        this.name = name;
        this.description = description;
        this.formLink = formLink;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormLink() {
        return formLink;
    }
    public void setFormLink(String formLink) {
        this.formLink = formLink;
    }
}

