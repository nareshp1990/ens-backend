package com.ens.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("Content-Type", "token", "Boundary-Id", "Access-Control-Request-Method",
                        "Access-Control-Allow-Origin", "Access-Control-Request-Headers",
                        "Access-Control-Allow-Methods", "product-type","X-Requested-With",
                        "Origin", "Accept")
                .exposedHeaders("Access-Control-Allow-Origin", "Boundary-Id", "Connection",
                        "Content-Disposition", "product-type")
                .allowedMethods("GET", "PUT", "OPTIONS", "POST", "DELETE", "PATCH","HEAD","TRACE","CONNECT")
                .maxAge(36000);
    }
}