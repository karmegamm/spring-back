package com.SpringCrud.Springcrud.Controller;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> createBook(@ModelAttribute BookDTO bookDTO) {
        try {
            bookService.saveBook(bookDTO);
            return ResponseEntity.ok("Book created successfully");
        } catch (Exception e) {
            // Handle any exceptions, e.g., file upload or database errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the book.");
        }
    }
}
