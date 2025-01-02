package com.carWale.urlshortener.serverLayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.carWale.urlshortener.serverLayer.model.Url;
import com.carWale.urlshortener.serverLayer.service.CountServerService;
import com.carWale.urlshortener.serverLayer.service.UrlServerService;

@Controller
public class UrlServerController {
	
	@Autowired
    private UrlServerService urlService;
	
	@Autowired
    private CountServerService countService;
	
	/**
     * Generate a shortened URL for the given original URL.
     * @param originalUrl the original URL
     * @return the shortened URL
     */
    public String shortenUrl(String originalUrl,String email) {
    	
    	return urlService.shortenUrl(originalUrl,email);
    }
    
    /**
     * Retrieve the original URL from a shortened URL.
     * @param shortUrl the shortened URL
     * @return the original URL
     */
    public Url getOriginalUrl(String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }
    
    /**
     * Increment the click count of shortUrl.
     * @param Url model
     * @return void
     */
    public void incrementClickCount(Url url) {
    	countService.incrementClickCount(url);
    }
    
    /**
     * Get the click count of shortUrl.
     * @param shortUrl the shortened URL
     * @return count of click 
     */
    public Url getClickCount(String shortUrl) {
        return countService.getClickCount(shortUrl);
    }
    
    /**
     * Get a history of the user.
     * @param email id of the user
     * @return List of Url objects.  
     */
	public List<Url> getHistoryOfUser(String emailId) {
		return urlService.getHistoryOfUser(emailId);
	}
}
