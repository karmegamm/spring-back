package com.SpringCrud.Springcrud.Services.IMPL;

import com.SpringCrud.Springcrud.DTO.UserSignUpDTO;
import com.SpringCrud.Springcrud.Entity.Users;
import com.SpringCrud.Springcrud.Repo.SignUpRepo;
import com.SpringCrud.Springcrud.Services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceIMPL implements AuthenticateService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    SignUpRepo signUpRepo;
    @Override
    public Users register(UserSignUpDTO userSignUpDTO) {
        Users user = new Users(
                userSignUpDTO.getUsername(),
                userSignUpDTO.getEmail(),
                this.passwordEncoder.encode(userSignUpDTO.getPassword()),
                false
        );
        signUpRepo.save(user);

        return user;
    }

    @Override
    public  Users  login(UserSignUpDTO userSignUpDTO) {
        Users user = signUpRepo.findByEmail(userSignUpDTO.getEmail());
        if(user!=null){
            boolean isAuthed = passwordEncoder.matches(userSignUpDTO.getPassword(), user.getPassword());
            if(isAuthed){
                return user;
            }
        }
        return null;
    }
}
