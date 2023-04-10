package com.urlshortener.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "urlShortener")
@Data
@NoArgsConstructor
public class UrlShortener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullUrl")
    private String fullUrl;
    @Column(name = "shortUrl")
    private String shortUrl;
    @CreatedDate
    @Column(name = "created")
    private LocalDate created;

    public UrlShortener(String fullUrl,String shortUrl) {
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
    }
}
