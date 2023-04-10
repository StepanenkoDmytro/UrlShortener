package com.urlshortener.UrlShortener.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private Status status;
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = Status.ACTIVE;
        this.role = "ROLE_USER";
    }
}

