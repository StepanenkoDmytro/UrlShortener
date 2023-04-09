package com.urlshortener.UrlShortener.repository;

import com.urlshortener.UrlShortener.entity.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
