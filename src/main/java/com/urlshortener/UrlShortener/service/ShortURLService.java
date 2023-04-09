package com.urlshortener.UrlShortener.service;

import com.urlshortener.UrlShortener.entity.ShortURL;

import java.util.List;
import java.util.Map;

public interface ShortURLService {
    Map<String, String> getAll();
    String getByFullURL(String fullURL);
    String getByShortURL(String shortURL);
    void save(String longURL);
}
