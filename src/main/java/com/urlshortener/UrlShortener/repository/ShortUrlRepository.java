package com.urlshortener.UrlShortener.repository;

import com.urlshortener.UrlShortener.entity.ShortURL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface ShortUrlRepository {
    Map<String, String> findAll();
    String findByShortURL(String shortURL);
    String findByFullURL(String fullURL);
    void save(String shortURL, String longURL);
    void delete(String shortURL);

}
