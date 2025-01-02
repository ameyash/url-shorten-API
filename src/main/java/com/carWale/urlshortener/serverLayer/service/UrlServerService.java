package com.carWale.urlshortener.serverLayer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.InvalidUrlException;

import com.carWale.urlshortener.serverLayer.model.Url;
import com.carWale.urlshortener.serverLayer.model.User;
import com.carWale.urlshortener.serverLayer.repository.UrlRepository;
import com.carWale.urlshortener.serverLayer.repository.UserRepository;
import com.carWale.urlshortener.serverLayer.service.CacheService;
import com.carWale.urlshortener.serverLayer.service.IdGeneratorService;

import java.util.List;
import java.util.Optional;

@Service
public class UrlServerService {
	
	@Autowired
    private UrlRepository urlRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private IdGeneratorService generatorService;
    
	/**
     * Generate a shortened URL for the given original URL.
     * @param originalUrl the original URL
     * @return the shortened URL
     */
    public String shortenUrl(String originalUrl, String email) {
        // Check cache first
    	
    	if(!isValidUrl(originalUrl)) {
    		throw new InvalidUrlException("Invalid URL.");
    	}
    	
    	// Check if URL already exists in the database or cache
        Url existingUrl = urlRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> {
                    String shortUrl = generateShortUrl();
                    Url url = new Url();
                    Optional<User> userId = userRepository.findByEmail(email);
                    url.setOriginalUrl(originalUrl);
                    url.setShortUrl(generateShortUrl());
                    url.setCreatedAt(java.time.LocalDateTime.now());
                    url.setUser(userId.get());
                    urlRepository.save(url); // Save to repository
                    cacheService.addToCache(shortUrl, url); // Add to cache
                    
                    return url;
                });

        return existingUrl.getShortUrl();
    }
    
    /**
     * Retrieve the original URL from a shortened URL.
     * @param shortUrl the shortened URL
     * @return the original URL
     */
    public Url getOriginalUrl(String shortUrl) {
    	// Check cache first
        Url cachedUrl = cacheService.getFromCache(shortUrl);
        if (cachedUrl != null) {
            return cachedUrl;
        }

        // If not in cache, check the database
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> {
                    return new InvalidUrlException("Short URL not found.");
                });

        // Add result to cache
        cacheService.addToCache(shortUrl, url);

        return url;
    }
    
    /**
     * Get a history of the user.
     * @param email id of the user
     * @return List of Url objects.  
     */
    public List<Url> getHistoryOfUser(String emailId) {
    	return urlRepository.findByUser(userRepository.findByEmail(emailId).get()).orElse(null);
    }
    
    /**
     * Generate a random UUID for shortened URL.
     * @param 
     * @return the random UUID
     */
    private String generateShortUrl() {
        return generatorService.getShortUrl();
    }
    
    /**
     * check URL is valid.
     * @param originalUrl the original URL
     * @return boolean value: is valid or not
     */
    private boolean isValidUrl(String originalUrl) {
        String urlRegex = "^(http://|https://).*";
        return originalUrl.matches(urlRegex);
    }


}