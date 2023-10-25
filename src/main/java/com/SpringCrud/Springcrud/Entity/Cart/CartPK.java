package com.SpringCrud.Springcrud.Entity.Cart;

import java.io.Serializable;
import java.util.Objects;

public class    CartPK implements Serializable {
    private Long user_id;
    private Long book_id;

    public CartPK() {
    }

    public CartPK(Long user_id, Long book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartPK cartPK = (CartPK) o;
        return Objects.equals(user_id, cartPK.user_id) && Objects.equals(book_id, cartPK.book_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, book_id);
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }
}