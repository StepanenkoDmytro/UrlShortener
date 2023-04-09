package com.urlshortener.UrlShortener.controller;

import com.urlshortener.UrlShortener.service.ShortURLService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/")
public class URLController {
    /*
    Дописати encode and decode for shortLink
    Додати Base auth
    Написати тести
     */
    private final ShortURLService shortURLService;

    public URLController(ShortURLService shortURLService) {
        this.shortURLService = shortURLService;
    }

    @PostMapping("shortener")
    public ResponseEntity<Object> addLong(@RequestParam String url){
        shortURLService.save(url);
        String shortURL = "localhost:8080/api/v1/" + shortURLService.getByFullURL(url);
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Object> getShort(){
        Map<String, String> shortURL = shortURLService.getAll();
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("{shortURL}")
    public ResponseEntity<Object> redirect(@PathVariable String shortURL){
        HttpHeaders headers = new HttpHeaders();
        String longURL = shortURLService.getByShortURL(shortURL);
        headers.setLocation(URI.create(longURL));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
