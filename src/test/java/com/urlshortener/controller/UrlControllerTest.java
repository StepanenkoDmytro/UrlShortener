package com.urlshortener.controller;

import com.urlshortener.dto.FullUrl;
import com.urlshortener.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlControllerTest {
    @Mock
    private UrlShortenerService urlShortenerService;
    @InjectMocks
    private UrlController urlController;
    @Value("${base.url}")
    private String baseUrl;

    @Test
    void invalidFullUrlThenReturnBadRequest(){
        FullUrl fullUrl = new FullUrl();
        fullUrl.setFullUrl("invalid");

        ResponseEntity<String> response = urlController.saveUrl(fullUrl);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Url is not valid", response.getBody());
        verifyNoInteractions(urlShortenerService);
    }

    @Test
    void existFullUrlThenReturnConflict(){
        FullUrl fullUrl = new FullUrl();
        fullUrl.setFullUrl("http://google.com");
        when(urlShortenerService.isExistByFullUrl(fullUrl.getFullUrl())).thenReturn(true);

        ResponseEntity<String> response = urlController.saveUrl(fullUrl);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        String message = String.format("UrlShortener with shortUrl = %s already exist", fullUrl.getFullUrl());
        assertEquals(message, response.getBody());

        verify(urlShortenerService).isExistByFullUrl(fullUrl.getFullUrl());
        verifyNoMoreInteractions(urlShortenerService);
    }

    @Test
    void validFullUrlThenReturnShortUrlAndStatusOk(){
        FullUrl fullUrl = new FullUrl();
        fullUrl.setFullUrl("http://google.com");
        String shortUrl = "adc123";

        when(urlShortenerService.getByFullUrl(fullUrl.getFullUrl())).thenReturn(shortUrl);
        when(urlShortenerService.isExistByFullUrl(fullUrl.getFullUrl())).thenReturn(false);

        ResponseEntity<String> response = urlController.saveUrl(fullUrl);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        String expectedResponse = baseUrl + shortUrl;
        assertEquals(expectedResponse, response.getBody());

        verify(urlShortenerService).isExistByFullUrl(fullUrl.getFullUrl());
        verify(urlShortenerService).getByFullUrl(fullUrl.getFullUrl());
        verify(urlShortenerService).registered(fullUrl.getFullUrl());
    }

    @Test
    void redirectToFullUrl(){
        String shortUrl = "abc123";
        String fullUrl = "http://google.com";
        HttpHeaders expectedHeader = new HttpHeaders();
        expectedHeader.setLocation(URI.create(fullUrl));
        when(urlShortenerService.getByShortUrl(shortUrl)).thenReturn(fullUrl);

        ResponseEntity<Object> response = urlController.redirect(shortUrl);

        assertEquals(HttpStatus.MOVED_PERMANENTLY, response.getStatusCode());
        assertEquals(expectedHeader, response.getHeaders());
    }

    @Test
    void redirectNotFound(){
        String shortUrl = "abc123";
        when(urlShortenerService.getByShortUrl(shortUrl)).thenReturn(null);

        ResponseEntity<Object> response = urlController.redirect(shortUrl);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("URL: abc123 not found", response.getBody());
    }
}

