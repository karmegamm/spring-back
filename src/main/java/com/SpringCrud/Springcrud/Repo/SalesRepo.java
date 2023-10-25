package com.SpringCrud.Springcrud.Repo;

import com.SpringCrud.Springcrud.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Long> {
    // You can add custom query methods here if needed
}