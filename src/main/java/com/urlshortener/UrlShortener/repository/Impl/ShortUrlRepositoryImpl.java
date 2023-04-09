package com.urlshortener.UrlShortener.repository.Impl;

import com.urlshortener.UrlShortener.repository.ShortUrlRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShortUrlRepositoryImpl implements ShortUrlRepository {
    private final Map<String, String> urlMap = new HashMap<>();

    @Override
    public Map<String, String> findAll() {
        return urlMap;
    }

    @Override
    public String findByShortURL(String shortURL) {
        return urlMap.get(shortURL);
    }

    @Override
    public String findByFullURL(String fullURL) {
        for(String key : urlMap.keySet()){
            String value = urlMap.get(key);
            if(value.equals(fullURL))
                return key;
        }
        throw new RuntimeException(String.format("Not found fullURL = %s", fullURL));
    }

    @Override
    public void save(String shortURL, String longURL) {
        urlMap.put(shortURL, longURL);
    }

    @Override
    public void delete(String shortURL) {
        urlMap.remove(shortURL);
    }

    @Override
    public boolean isExist(String longURL) {

        return urlMap.containsValue(longURL);
    }
}
