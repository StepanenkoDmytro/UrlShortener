package com.urlshortener.service;

import com.urlshortener.entity.UrlShortener;

import java.util.List;

public interface UrlShortenerService {
    List<UrlShortener> getAll();
    String getByFullUrl(String fullURL);
    String getByShortUrl(String shortURL);
    void registered(String longURL);
    boolean isExistByFullUrl(String fullUrl);
}
