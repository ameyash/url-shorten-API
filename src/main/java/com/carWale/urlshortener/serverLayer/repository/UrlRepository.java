package com.carWale.urlshortener.serverLayer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carWale.urlshortener.serverLayer.model.Url;
import com.carWale.urlshortener.serverLayer.model.User;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
    Optional<Url> findByOriginalUrl(String originalUrl);
    Optional<List<Url>> findByUser(User user);
}
