package com.SpringCrud.Springcrud.Repo;

import com.SpringCrud.Springcrud.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface SignUpRepo extends JpaRepository<Users,Integer> {
    Optional<Users> findOneByEmailAndPassword(String email,String password);
    Users findByEmail(String email);
}
