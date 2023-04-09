package com.urlshortener.UrlShortener.service;

import java.util.Map;

public interface ShortUrlService {
    Map<String, String> getAll();
    String getByFullUrl(String fullURL);
    String getByShortUrl(String shortURL);
    void registered(String longURL);
}
