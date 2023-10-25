package com.SpringCrud.Springcrud.Services.IMPL;

import com.SpringCrud.Springcrud.DTO.BookDTO;
import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.Cart.Cart;
import com.SpringCrud.Springcrud.Repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {
    @Autowired
    private CartRepo cartRepository;

    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Long> getBooksByUserId(Long userId) {
        return cartRepository.findBookIdsByUserId(userId);
    }
}

