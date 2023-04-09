package com.urlshortener.UrlShortener.controller;

import com.urlshortener.UrlShortener.service.ShortUrlService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UrlController {
    /*
    Написати тести
     */
    private final ShortUrlService shortUrlService;

    public UrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("shortener")
    public ResponseEntity<Object> addLong(@RequestParam String url){

        UrlValidator urlValidator = new UrlValidator();
        if(!urlValidator.isValid(url)){
            return new ResponseEntity<>("Url is not valid", HttpStatus.BAD_REQUEST);
        }
        shortUrlService.registered(url);
        String shortURL = shortUrlService.getByFullUrl(url);
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Object> getShort(){
        Map<String, String> shortURL = shortUrlService.getAll();
        return new ResponseEntity<>(shortURL, HttpStatus.OK);
    }

    @GetMapping("{shortURL}")
    public ResponseEntity<Object> redirect(@PathVariable String shortURL){
        HttpHeaders headers = new HttpHeaders();
        String longURL = shortUrlService.getByShortUrl(shortURL);
        headers.setLocation(URI.create(longURL));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
