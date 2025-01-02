package com.carWale.urlshortener.serverLayer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carWale.urlshortener.serverLayer.model.Url;
import com.carWale.urlshortener.serverLayer.repository.UrlRepository;

@Service
public class CountServerService {
	
	@Autowired
    private UrlRepository urlRepository;
	
	@Autowired
	private CacheService cacheService;
	/**
     * Increment the click count of shortUrl.
     * @param Url model
     * @return void
     */
    public void incrementClickCount(Url url) {
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);
    }
    
    /**
     * Get the click count of shortUrl.
     * @param shortUrl the shortened URL
     * @return count of click 
     */
    public Url getClickCount(String shortUrl) {
    	Url cachedUrl = cacheService.getFromCache(shortUrl);
        if (cachedUrl != null) {
            return cachedUrl;
        }
    	
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new IllegalArgumentException("URL not found"));
        return url;
    }
}
