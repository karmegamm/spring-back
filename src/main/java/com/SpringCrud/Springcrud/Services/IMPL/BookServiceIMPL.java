package com.SpringCrud.Springcrud.Services.IMPL;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.BookKey;
import com.SpringCrud.Springcrud.Entity.Title;
import com.SpringCrud.Springcrud.Repo.BookRepo;
import com.SpringCrud.Springcrud.Repo.TitleRepo;
import com.SpringCrud.Springcrud.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceIMPL implements BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private TitleRepo titleRepo;
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


    @Override
    public void addTitle(String title, MultipartFile image) {
        try {
            // Create a new Title entity and set its attributes
            Title newTitle = new Title();
            newTitle.setTitle(title);
            // Check if an image is provided
            if (image != null && !image.isEmpty()) {
                // Set the image data (byte array) after converting it from the MultipartFile
                newTitle.setImage(image.getBytes());
            }

            // Save the entity to the database using a Spring Data JPA repository
            titleRepo.save(newTitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Title> getTitles(Pageable pageable) {
        List<Title> newTitle = new ArrayList<>();
        try{
            Page<Title> titles= titleRepo.findAll(pageable);
            newTitle=titles.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newTitle;
    }

    @Override
    public List<Book> getbooksbytitle(String title,Pageable pageable) {
        List<Book> newBook = new ArrayList<>();
        try{
            Page<Book> books= bookRepo.findByTitle(title,pageable);
            newBook=books.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newBook;
    }

}
