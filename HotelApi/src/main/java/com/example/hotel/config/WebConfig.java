package com.example.hotel.config;

import com.example.hotel.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**");
        // Specify the paths to apply the interceptor. Here, it applies to all paths starting with /api.
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow CORS for all paths
                .allowedOrigins("http://localhost:3000")  // Allow specific origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow sending of credentials (cookies, etc.)
    }
}
