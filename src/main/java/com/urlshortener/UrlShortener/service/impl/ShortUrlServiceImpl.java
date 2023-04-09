package com.urlshortener.UrlShortener.service.impl;

import com.urlshortener.UrlShortener.repository.ShortUrlRepository;
import com.urlshortener.UrlShortener.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;

@Service
@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {
    private final ShortUrlRepository shortUrlRepository;
    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    @Override
    public Map<String, String> getAll() {
        Map<String, String> result = shortUrlRepository.findAll();
        log.info("IN getAll - {} links found", result.size());
        return result;
    }

    @Override
    public String getByFullUrl(String fullUrl) {
        String shortUrl = shortUrlRepository.findByFullURL(fullUrl);
        log.info("IN getByFullUrl - shortUrl: {} found by fullUrl: {}", shortUrl, fullUrl);
        return shortUrl;
    }

    @Override
    public String getByShortUrl(String shortUrl) {
        String fullUrl = shortUrlRepository.findByShortURL(shortUrl);
        log.info("IN getByShortUrl - fullUrl: {} found by shortUrl: {}", fullUrl, shortUrl);
        return fullUrl;
    }

    @Override
    public void registered(String longUrl) {
        String shortURL = generateShortUrl(longUrl);
        shortUrlRepository.save(shortURL, longUrl);
        log.info("IN save - longUrl: {} successfully registered with shortUrl: {}", longUrl, shortURL);
    }

    public String generateShortUrl(String longUrl) {
        if (shortUrlRepository.isExist(longUrl)) return shortUrlRepository.findByFullURL(longUrl);

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortURL = new StringBuilder(baseUrl);
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(characters.length());
            shortURL.append(characters.charAt(index));
        }
        return shortURL.toString();
    }
}
