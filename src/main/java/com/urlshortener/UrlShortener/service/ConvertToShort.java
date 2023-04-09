package com.urlshortener.UrlShortener.service;

public interface ConvertToShort {
    String encode(String longURL);
    String decode(String shortURL);
}
