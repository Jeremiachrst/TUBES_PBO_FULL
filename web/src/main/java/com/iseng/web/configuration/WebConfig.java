package com.iseng.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:3000") // Allow your frontend origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials if needed (e.g., cookies, auth headers)
    }
}