package com.SpringCrud.Springcrud.Services.IMPL;

import com.SpringCrud.Springcrud.Entity.Sales;
import com.SpringCrud.Springcrud.Repo.SalesRepo;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

    @Service
    public class OrderService {

        @Autowired
        private SalesRepo salesRepository;
        @Value("${razorpay.keyId}") // Add your Razorpay API key here
        private String razorpayKeyId;

        @Value("${razorpay.keySecret}") // Add your Razorpay API secret here
        private String razorpayKeySecret;

        public String createOrder(int amount) throws RazorpayException {
            RazorpayClient razorpay = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", amount * 100); // Amount should be in paise
            orderRequest.put("currency", "INR"); // You can change the currency code as needed
            orderRequest.put("receipt", "order_rcptid_" + System.currentTimeMillis());

            com.razorpay.Order order = razorpay.orders.create(orderRequest);

            return order.get("id");
        }

        public void saveSales(Sales sales) {
            salesRepository.save(sales);
        }
    }
