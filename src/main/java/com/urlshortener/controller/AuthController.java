package com.urlshortener.controller;

import com.urlshortener.dto.AuthRequestDto;
import com.urlshortener.entity.User;
import com.urlshortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("registration")
    public ResponseEntity<String> registration(@RequestBody AuthRequestDto user) {
        if (userService.isExist(user.getUsername())) {
            return new ResponseEntity<>("User is already exist", HttpStatus.BAD_REQUEST);
        }
        User newUser = new User(user.getUsername(), user.getPassword());
        userService.save(newUser);
        return new ResponseEntity<>("Successfully registered", HttpStatus.CREATED);
    }
}
