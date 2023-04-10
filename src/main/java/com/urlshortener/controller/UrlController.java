package com.urlshortener.controller;

import com.urlshortener.dto.FullUrl;
import com.urlshortener.entity.UrlShortener;
import com.urlshortener.service.UrlShortenerService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UrlController {
    /*
    Написати тести
     */
    private final UrlShortenerService shortUrlService;
    @Value("${base.url}")
    private String baseUrl;

    @Autowired
    public UrlController(UrlShortenerService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("shortener")
    public ResponseEntity<String> addLong(@RequestBody FullUrl url) {
        String fullUrl = url.getFullUrl();
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(fullUrl)) {
            return new ResponseEntity<>("Url is not valid", HttpStatus.BAD_REQUEST);
        }

        if (shortUrlService.isExistByFullUrl(fullUrl)) {
            String message = String.format("UrlShortener with shortUrl = %s already exist", fullUrl);
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }
        shortUrlService.registered(fullUrl);
        String shortURL = baseUrl + shortUrlService.getByFullUrl(fullUrl);
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Object> getShort() {
        List<UrlShortener> shortURL = shortUrlService.getAll();
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("{shortURL}")
    public ResponseEntity<Object> redirect(@PathVariable String shortURL) {
        HttpHeaders headers = new HttpHeaders();
        String longURL = shortUrlService.getByShortUrl(shortURL);
        headers.setLocation(URI.create(longURL));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
