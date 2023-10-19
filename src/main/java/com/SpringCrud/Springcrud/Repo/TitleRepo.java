package com.SpringCrud.Springcrud.Repo;

import com.SpringCrud.Springcrud.Entity.Title;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface TitleRepo extends JpaRepository<Title, Long> {
    Page<Title> findAll(Pageable pageable);

    // Define a custom query method to select id and title fields
    @Query("SELECT new Title(t.id, t.title) FROM Title t")
    List<Title> findAllTitles();
}
