package com.SpringCrud.Springcrud.Repo;

import com.SpringCrud.Springcrud.Entity.Book;
import com.SpringCrud.Springcrud.Entity.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface BookRepo extends JpaRepository<Book,Integer> {
    Page<Book> findByTitle(String title, Pageable pageable);
    Optional<Book> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT b FROM Book b WHERE b.id IN :bookIds")
    List<Book> findAllByIds(@Param("bookIds") List<Long> bookIds);

}
