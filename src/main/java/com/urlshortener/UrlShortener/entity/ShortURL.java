package com.urlshortener.UrlShortener.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShortURL {
    private String shotURL;
    private LocalDate localDate;

    public ShortURL(String shotURL, LocalDate localDate) {
        this.shotURL = shotURL;
        this.localDate = localDate;
    }
}
