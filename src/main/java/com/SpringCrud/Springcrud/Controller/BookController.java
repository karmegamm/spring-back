package com.SpringCrud.Springcrud.Controller;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Entity.*;
import com.SpringCrud.Springcrud.Entity.Cart.Cart;
import com.SpringCrud.Springcrud.Repo.TitleRepo;
import com.SpringCrud.Springcrud.Services.BookService;
import com.SpringCrud.Springcrud.Services.IMPL.CartService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    private CartService cartService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PersistenceContext
    private EntityManager entityManager;

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

    @PutMapping("/update-stock")
    public ResponseEntity<String> updateStock(@RequestParam Long bookId, @RequestParam int quantityToSubtract) {
        try {
            // Call the service to update the stock quantity
            bookService.subtractStock(bookId, quantityToSubtract);
            return ResponseEntity.ok("Stock quantity updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating stock quantity: " + e.getMessage());
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


    @PostMapping("/add-to-cart")
    public ResponseEntity<String> createCartEntry(
            @RequestParam Long bookId,
            @RequestParam Long userId
    ) {
        try {
            Cart cart = new Cart(bookId, userId);
            cartService.addToCart(cart);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cart entity saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving Cart entity: " + e.getMessage());
        }
    }

    @GetMapping("/get-cart-books-by-userid")
    public ResponseEntity<List<Long>> getBookstoCart(
            @RequestParam Long userId
    ) {
        List<Long> bookIds ;
        try {
            bookIds=cartService.getBooksByUserId(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookIds);
        } catch (Exception e) {
            return (ResponseEntity<List<Long>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/get-books-in-ids")
    public ResponseEntity<List<Book>> getBooksByIds(@RequestBody List<Long> bookIds) {
        List<Book> books ;
        try {
            books= bookService.getBooksByIds(bookIds);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
