package com.SpringCrud.Springcrud.DTO;

import org.springframework.web.multipart.MultipartFile;

public class BookDTO {
    private String bookName;
    private String author;
    private String title;
    private double price;
    private int stockQuantity;
    private String description;
    private MultipartFile bookImageFile; // Represents the uploaded image file

    public BookDTO() {
        // Default constructor
    }

    public BookDTO(String bookName, String author, String title, double price, int stockQuantity, String description, MultipartFile bookImageFile) {
        this.bookName = bookName;
        this.author = author;
        this.title = title;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.bookImageFile = bookImageFile;
    }

    // Getters and setters for each field

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getBookImageFile() {
        return bookImageFile;
    }

    public void setBookImageFile(MultipartFile bookImageFile) {
        this.bookImageFile = bookImageFile;
    }
}

