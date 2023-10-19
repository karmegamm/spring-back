package com.SpringCrud.Springcrud.Controller;

import com.SpringCrud.Springcrud.Services.IMPL.OrderService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/create/{amount}")
    public String createOrder(@PathVariable int amount) {
        try {
            return orderService.createOrder(amount);
        } catch (RazorpayException e) {
            e.printStackTrace();
            return "Error creating order";
        }
    }
}
