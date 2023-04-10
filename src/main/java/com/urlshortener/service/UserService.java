package com.urlshortener.service;

import com.urlshortener.entity.User;

public interface UserService {
    void save(User user);
    boolean isExist(String username);
}
