package com.SpringCrud.Springcrud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookKey implements Serializable {
    @Column(name = "book_name",length = 255)
    private String bookName;

    @Column(name = "author",length = 254)
    private String author;

    public BookKey() {
    }

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

    public BookKey(String bookName, String author) {
        this.bookName = bookName;
        this.author = author;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookKey personKey = (BookKey) o;
        return author == personKey.author &&
                Objects.equals(bookName, personKey.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author);
    }
    @Override
    public String toString() {
        return "BookKey{" +
                "bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
