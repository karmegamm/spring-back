package com.SpringCrud.Springcrud.Controller;

import com.SpringCrud.Springcrud.DTO.UserSignUpDTO;
import com.SpringCrud.Springcrud.Entity.Users;
import com.SpringCrud.Springcrud.Services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticateService authServise;

    @PostMapping("/sign-up")
    public Users RegisterNewUser(@RequestBody UserSignUpDTO userSignUpDTO){
        Users s = authServise.register(userSignUpDTO);
        return s;
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?>  LoginUser(@RequestBody UserSignUpDTO userSignUpDTO){
        Users s = authServise.login(userSignUpDTO);
        if(s!=null){
            return ResponseEntity.ok(s);
        }else{
            String errorMessage = "Authentication failed. Please check your credentials.";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }

}
