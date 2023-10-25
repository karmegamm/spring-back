package com.SpringCrud.Springcrud.Repo;

import com.SpringCrud.Springcrud.Entity.Cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    @Query("SELECT c.book_id FROM Cart c WHERE c.user_id = :userId")
    List<Long> findBookIdsByUserId(@Param("userId") Long userId);
}
