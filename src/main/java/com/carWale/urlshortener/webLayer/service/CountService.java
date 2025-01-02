package com.carWale.urlshortener.webLayer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carWale.urlshortener.serverLayer.controller.UrlServerController;
import com.carWale.urlshortener.serverLayer.model.Url;
import com.carWale.urlshortener.serverLayer.repository.UrlRepository;

@Service
public class CountService {
	@Autowired
    private UrlServerController urlController;
	
	/**
     * Increment the click count of shortUrl.
     * @param Url model
     * @return void
     */
    public void incrementClickCount(Url url) {
    	urlController.incrementClickCount(url);
    }
    
    /**
     * Get the click count of shortUrl.
     * @param shortUrl the shortened URL
     * @return count of click 
     */
    public Url getClickCount(String shortUrl) {
    	return urlController.getClickCount(shortUrl);
    }

    /**
     * Get a history of the user.
     * @param email id of the user
     * @return List of Url objects.  
     */
	public List<Url> getHistoryOfUser(String emailId) {
		return urlController.getHistoryOfUser(emailId);
	}
}
