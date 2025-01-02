package com.carWale.urlshortener.webLayer.utils;


import io.github.bucket4j.Bucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RateLimiterService implements Filter {
	
	@Autowired
    private Bucket bucket;


    /**
     * Intercepts incoming requests and applies rate limiting.
     * If a token is available, the request is processed; otherwise, a 429 status code is returned.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (bucket.tryConsume(1)) {
            chain.doFilter(request, response); // Forward the request if rate limiting is not hit
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(429); // Set 429 status
            httpResponse.setContentType("application/json"); // Specify the content type
            httpResponse.getWriter().write("{\"error\": \"Rate limit exceeded. Try again later.\"}"); // JSON error
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}