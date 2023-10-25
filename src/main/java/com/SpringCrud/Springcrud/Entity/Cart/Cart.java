package com.SpringCrud.Springcrud.Entity.Cart;

import jakarta.persistence.*;


@Entity
@IdClass(CartPK.class)
public class Cart {
    @Id
    private Long book_id;
    @Id
    private Long user_id;


    public Cart(Long book_id, Long user_id) {
        this.book_id = book_id;
        this.user_id = user_id;
    }


    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Cart() {
    }

}
