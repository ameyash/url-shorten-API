package com.carWale.urlshortener.webLayer.dto;

public class UrlShortenResponse {
	
	private String shortUrl;

    public UrlShortenResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
}
