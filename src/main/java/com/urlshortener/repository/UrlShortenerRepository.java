package com.urlshortener.repository;

import com.urlshortener.entity.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Long> {
    Optional<UrlShortener> findByFullUrl(String fullUrl);

    //    List<UrlShortener> findAll();
    Optional<UrlShortener> findByShortUrl(String shortUrl);

    //    void save(String shortURL, String longURL);
//    void delete(String shortURL);
//    boolean isExistByFullUrl(String fullUrl);

}
