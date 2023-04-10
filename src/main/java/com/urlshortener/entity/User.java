package com.urlshortener.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private Status status;
    @Column(name = "role")
    private String role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = Status.ACTIVE;
        this.role = "ROLE_USER";
    }
}

