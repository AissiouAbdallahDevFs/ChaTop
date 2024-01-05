package com.rentals.api.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Autowired
    SecurityConfig securityConfig;

    @Value("${jwt}")
    private String jwtSecret;


    public String getJwtSecret() {
        return jwtSecret;
    }
  
    
}
