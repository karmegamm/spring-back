package com.SpringCrud.Springcrud.Controller;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.Title;
import com.SpringCrud.Springcrud.Repo.TitleRepo;
import com.SpringCrud.Springcrud.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.data.domain.Pageable;

@RestController
@CrossOrigin
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    private TitleRepo titleRepo;
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
    @PostMapping(value = "/newtitle")
    public ResponseEntity<?> saveTitle(@RequestParam("title") String title, @RequestParam("image") MultipartFile image) {
        try {
            bookService.addTitle(title, image);
            return ResponseEntity.ok("Title created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the title.");
        }
    }

    @GetMapping(value = "/gettitles")
    public ResponseEntity<?> getTitles(@PageableDefault(size = 5) Pageable pageable) {
        try {
            List<Title> newTitles = bookService.getTitles( pageable);
            return ResponseEntity.ok(newTitles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the title.");
        }
    }

    @GetMapping(value = "/getbooksbytitles")
    public ResponseEntity<?> getBooksByTitle(@RequestParam("title") String title, @PageableDefault(size = 5) Pageable pageable) {
        try {
            List<Book> newBooks = bookService.getbooksbytitle(title, pageable);
            return ResponseEntity.ok(newBooks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating the title.");
        }
    }

    @GetMapping(value = "/get-all-titles-only")
    public ResponseEntity<List<Title>> getAllTitles() {
        List<Title> titles = titleRepo.findAllTitles();
        return ResponseEntity.ok(titles);
    }
}
