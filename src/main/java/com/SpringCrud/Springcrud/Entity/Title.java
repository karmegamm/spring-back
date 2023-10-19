package com.SpringCrud.Springcrud.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Lob
    @Column(name = "image", nullable = true,columnDefinition = "BLOB")
    private byte[] image;

    public Title() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Title(Long id, String title, byte[] image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public Title(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Title(String title, byte[] image) {
        this.title = title;
        this.image = image;
    }
}
