package com.SpringCrud.Springcrud.Services.IMPL;

import com.SpringCrud.Springcrud.DTO.UpdateDTO;
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

    @Override
    public Users updateProfile(Long userId, UpdateDTO updateDTO) {
        Users user = signUpRepo.findByUserid(userId);

        if (user == null) {
            return null; // User not found
        }

        if (!passwordEncoder.matches(updateDTO.getOld_password(), user.getPassword())) {
            return null; // Old password doesn't match
        }

        // Check if the new fields are not null or empty
        if (updateDTO.getUsername() == null || updateDTO.getUsername().isEmpty() ||
                updateDTO.getEmail() == null || updateDTO.getEmail().isEmpty() ||
                updateDTO.getCurrent_password() == null || updateDTO.getCurrent_password().isEmpty()) {
            return null; // Invalid input data
        }
        // Update the user's profile
        user.setUsername(updateDTO.getUsername());
        user.setEmail(updateDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(updateDTO.getCurrent_password()));
        signUpRepo.save(user);

        return user;
    }
}
