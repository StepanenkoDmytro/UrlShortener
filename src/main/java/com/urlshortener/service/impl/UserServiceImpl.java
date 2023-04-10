package com.urlshortener.service.impl;

import com.urlshortener.entity.User;
import com.urlshortener.repository.UserRepository;
import com.urlshortener.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        String passwordEncode = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncode);
        userRepository.save(user);
        log.info("User with username = {} successfully registered", user.getUsername());
    }

    @Override
    public boolean isExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
