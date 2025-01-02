package com.carWale.urlshortener.serverLayer.service;

import org.springframework.stereotype.Service;

import com.carWale.urlshortener.serverLayer.model.Url;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    // In-memory cache using ConcurrentHashMap for thread-safety
    private final ConcurrentHashMap<String, Url> cache = new ConcurrentHashMap<>();

    /**
     * Retrieve the Url object from the cache by short URL.
     * 
     * @param shortUrl the short URL key
     * @return the Url object if present in the cache, null otherwise
     */
    public Url getFromCache(String shortUrl) {
        return cache.get(shortUrl);
    }

    /**
     * Add a Url object to the cache.
     * 
     * @param shortUrl the short URL key
     * @param url      the Url object to store
     */
    public void addToCache(String shortUrl, Url url) {
        cache.put(shortUrl, url);
    }

    /**
     * Invalidate (remove) an entry from the cache by short URL.
     * 
     * @param shortUrl the short URL key
     */
    public void invalidateCache(String shortUrl) {
        cache.remove(shortUrl);
    }

    /**
     * Clear the entire cache.
     */
    public void clearCache() {
        cache.clear();
    }
}
