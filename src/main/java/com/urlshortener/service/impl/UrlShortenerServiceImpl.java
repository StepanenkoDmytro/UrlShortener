package com.urlshortener.service.impl;

import com.urlshortener.entity.UrlShortener;
import com.urlshortener.exception.UrlShortenerFetchException;
import com.urlshortener.repository.UrlShortenerRepository;
import com.urlshortener.service.UrlShortenerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class UrlShortenerServiceImpl implements UrlShortenerService {
    private final UrlShortenerRepository urlShortenerRepository;
    @Autowired
    public UrlShortenerServiceImpl(UrlShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    @Override
    public List<UrlShortener> getAll() {
        List<UrlShortener> result = urlShortenerRepository.findAll();
        log.info("IN getAll - {} links found", result.size());
        return result;
    }

    @Override
    public String getByFullUrl(String fullUrl) {
        UrlShortener url = urlShortenerRepository.findByFullUrl(fullUrl).orElseThrow(() ->
                new UrlShortenerFetchException(String.format("UrlShortener with fullUrl = %s not found", fullUrl)));
        log.info("IN getByFullUrl - shortUrl: {} found by fullUrl: {}", url.getShortUrl(), fullUrl);
        return url.getShortUrl();
    }

    @Override
    public String getByShortUrl(String shortUrl) {
        UrlShortener url = urlShortenerRepository.findByShortUrl(shortUrl).orElseThrow(() ->
                new UrlShortenerFetchException(String.format("UrlShortener with shortUrl = %s not found", shortUrl)));
        log.info("IN getByShortUrl - fullUrl: {} found by shortUrl: {}", url.getFullUrl(), shortUrl);
        return url.getFullUrl();
    }

    @Override
    public void registered(String longUrl) {
        String shortURL = generateShortUrl();
        UrlShortener newUrl = new UrlShortener(longUrl, shortURL);
        urlShortenerRepository.save(newUrl);
        log.info("IN save - longUrl: {} successfully registered with shortUrl: {}", longUrl, shortURL);
    }

    public String generateShortUrl() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortURL = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(characters.length());
            shortURL.append(characters.charAt(index));
        }
        return shortURL.toString();
    }

    public boolean isExistByFullUrl(String fullUrl) {
        Optional<UrlShortener> url = urlShortenerRepository.findByFullUrl(fullUrl);
        if (url.isPresent()) return true;
        return false;
    }
}
