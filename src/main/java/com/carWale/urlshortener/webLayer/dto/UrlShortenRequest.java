package com.carWale.urlshortener.webLayer.dto;

public class UrlShortenRequest {
	
	private String originalUrl;

	private String emailId;
	
	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

    // Getters and Setters
	
	
}
