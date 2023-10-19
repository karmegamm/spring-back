package com.SpringCrud.Springcrud.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    private BookKey bookKey;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "title",length = 255)
    private String title;

    @Column(name = "price")
    private double price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "book_image",columnDefinition = "BLOB")
    private byte[] bookImage;

    public Book() {
    }

    public Book(BookKey bookKey, String title, double price, int stockQuantity, String description, byte[] bookImage) {
        this.bookKey = bookKey;
        this.title = title;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.bookImage = bookImage;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public BookKey getBookKey() {
        return bookKey;
    }

    public void setBookKey(BookKey bookKey) {
        this.bookKey = bookKey;
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

    public byte[] getBookImage() {
        return bookImage;
    }

    public void setBookImage(byte[] bookImage) {
        this.bookImage = bookImage;
    }
}
