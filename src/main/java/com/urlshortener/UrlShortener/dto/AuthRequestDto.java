package com.urlshortener.UrlShortener.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
