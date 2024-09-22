package com.example.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow CORS for all paths
                .allowedOrigins("http://localhost:3000")  // Allow specific origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow sending of credentials (cookies, etc.)
    }
}
