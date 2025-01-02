package com.carWale.urlshortener.webLayer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carWale.urlshortener.serverLayer.controller.UrlServerController;
import com.carWale.urlshortener.serverLayer.model.Url;

@Service
public class UrlService {
    
    @Autowired
    private UrlServerController urlController;
    
    /**
     * Generate a shortened URL for the given original URL.
     * @param originalUrl the original URL
     * @return the shortened URL
     */
    public String shortenUrl(String originalUrl,String email) {
    	
    	return urlController.shortenUrl(originalUrl,email);
    }
    
    /**
     * Retrieve the original URL from a shortened URL.
     * @param shortUrl the shortened URL
     * @return the original URL
     */
    public Url getOriginalUrl(String shortUrl) {
        return urlController.getOriginalUrl(shortUrl);
    }
}