package com.SpringCrud.Springcrud.Services.IMPL;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.BookKey;
import com.SpringCrud.Springcrud.Repo.BookRepo;
import com.SpringCrud.Springcrud.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BookServiceIMPL implements BookService {
    @Autowired
    private BookRepo bookRepo;
    @Override
    public void saveBook(BookDTO bookDTO) throws IOException {
        String bookName = bookDTO.getBookName();
        String author = bookDTO.getAuthor();
        String title = bookDTO.getTitle();
        double price = bookDTO.getPrice();
        int stockQuantity = bookDTO.getStockQuantity();
        String description = bookDTO.getDescription();
        // Access the uploaded image file
        MultipartFile bookImageFile = bookDTO.getBookImageFile();

        // Save the image file (you can use a service for this)
        byte[] bookImage = bookImageFile.getBytes();

        // Create a new book entity
        Book book = new Book();
        book.setBookKey(new BookKey(bookName,author));
        book.setTitle(title);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.setDescription(description);
        book.setBookImage(bookImage);
        bookRepo.save(book);
    }
}
