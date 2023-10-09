package com.SpringCrud.Springcrud.Services;

import com.SpringCrud.Springcrud.DTO.UserSignUpDTO;
import com.SpringCrud.Springcrud.Entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticateService {

    Users register(UserSignUpDTO userSignUpDTO);

    Users login(UserSignUpDTO userSignUpDTO);
}
