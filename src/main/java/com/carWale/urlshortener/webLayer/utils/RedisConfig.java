package com.carWale.urlshortener.webLayer.utils;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

@Configuration
public class RedisConfig {

    /**
     * Configures a Bucket4j bucket with a capacity of 5 tokens that refills at a rate of 5 tokens per minute.
     * Each token represents the capacity to handle one request.
     */
    @Bean
    public Bucket bucket() {
        // Define the bandwidth with a limit of 5 tokens, refilled every minute
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        return Bucket4j.builder().addLimit(limit).build();
    }
}
