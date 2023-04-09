package com.urlshortener.UrlShortener.service.impl;

import com.urlshortener.UrlShortener.entity.ShortURL;
import com.urlshortener.UrlShortener.repository.ShortUrlRepository;
import com.urlshortener.UrlShortener.service.ShortURLService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ShortURLServiceImpl implements ShortURLService {
    private final ShortUrlRepository shortUrlRepository;

    public ShortURLServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public Map<String, String> getAll() {
        return shortUrlRepository.findAll();
    }

    @Override
    public String getByFullURL(String fullURL) {
        return shortUrlRepository.findByFullURL(fullURL);
    }

    @Override
    public String getByShortURL(String shortURL) {

        return shortUrlRepository.findByShortURL(shortURL);
    }

    @Override
    public void save(String longURL) {
//        ShortURL shortURL = new ShortURL("short-link", LocalDate.now());

        shortUrlRepository.save("short-link", longURL);
    }
}
