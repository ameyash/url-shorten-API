package com.carWale.urlshortener.webLayer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carWale.urlshortener.webLayer.dto.UrlShortenRequest;
import com.carWale.urlshortener.webLayer.dto.UrlShortenResponse;
import com.carWale.urlshortener.serverLayer.model.Url;
import com.carWale.urlshortener.webLayer.service.CountService;
import com.carWale.urlshortener.webLayer.service.UrlService;
import com.carWale.urlshortener.webLayer.utils.RateLimiterService;

@RestController
@RequestMapping("/api/url")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UrlController {
	
	@Autowired
    private UrlService urlService;
	
	@Autowired
	private CountService countService;
	
    /**
     * Shorten a long URL.
     * @param request the request containing the long URL
     * @return the shortened URL
     */
    @PostMapping("/shorten")
    public ResponseEntity<UrlShortenResponse> shortenUrl(@RequestBody UrlShortenRequest request) {
    	
    	String shortUrl = urlService.shortenUrl(request.getOriginalUrl(),request.getEmailId());
        return ResponseEntity.ok(new UrlShortenResponse(shortUrl));
    }
    
    
    /**
     * Redirect to the original URL.
     * @param shortUrl the shortened URL path
     * @return redirection to the original URL
     */
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        Url url = urlService.getOriginalUrl(shortUrl);
        countService.incrementClickCount(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(java.net.URI.create(url.getOriginalUrl()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    
    /**
     * Get a count of click.
     * @param shortUrl the shortened URL path
     * @return count of click 
     */
    @GetMapping("/analytics/{shortUrl}")
    public ResponseEntity<Url> getClickCount(@PathVariable String shortUrl) {
    	Url clickCount = countService.getClickCount(shortUrl);
        return ResponseEntity.ok(clickCount);
    }
    
    /**
     * Get a history of the user.
     * @param email id of the user
     * @return List of Url objects.  
     */
    @GetMapping("/analytics/history/{emailId}")
    public ResponseEntity<List<Url>> getHistoryOfUser(@PathVariable String emailId) {
    	List<Url> clickCount = countService.getHistoryOfUser(emailId);
        return ResponseEntity.ok(clickCount);
    }
}