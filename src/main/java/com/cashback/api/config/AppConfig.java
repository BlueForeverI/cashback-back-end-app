package com.cashback.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${facebook.app_secret}")
    private String facebookAppSecret;

    public String getFacebookAppSecret() {
        return facebookAppSecret;
    }
}
