package com.urlshortener.UrlShortener.controller;

import com.urlshortener.UrlShortener.entity.User;
import com.urlshortener.UrlShortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@Valid User user){
        if(userRepository.isExist(user)){
            return new ResponseEntity<>("User is exist", HttpStatus.BAD_GATEWAY);
        }
        userRepository.save(user);
        return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);
    }
}
