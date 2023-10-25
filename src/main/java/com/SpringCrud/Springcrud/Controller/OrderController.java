package com.SpringCrud.Springcrud.Controller;

import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.Sales;
import com.SpringCrud.Springcrud.Entity.Users;
import com.SpringCrud.Springcrud.Repo.SignUpRepo;
import com.SpringCrud.Springcrud.Services.IMPL.OrderService;
import com.razorpay.RazorpayException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private SignUpRepo signUpRepo;
    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/create/{amount}")
    public String createOrder(@PathVariable int amount) {
        try {
            return orderService.createOrder(amount);
        } catch (RazorpayException e) {
            e.printStackTrace();
            return "Error creating order";
        }
    }

    @PostMapping("/purchase-save")
    public ResponseEntity<String> savePurchase(@RequestBody Sales salesRequest) {
        try {
            // Retrieve the Book
            Long bookId = salesRequest.getBook().getBookId();
            Book book = entityManager.find(Book.class, bookId);

            if (book != null) {
                // Retrieve the User
                Long userId = salesRequest.getUser().getUserid();
                Users user = entityManager.find(Users.class, userId);

                if (user != null) {
                    Sales sales = new Sales();
                    sales.setUser(user);
                    sales.setBook(book);
                    sales.setDate(salesRequest.getDate());
                    sales.setQuantity(salesRequest.getQuantity());
                    sales.setTotalPrice(salesRequest.getTotalPrice());

                    orderService.saveSales(sales);

                    return ResponseEntity.status(HttpStatus.CREATED).body("Sales entity saved successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with ID " + userId + " not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book with ID " + bookId + " not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving Sales entity: " + e.getMessage());
        }
    }
}
