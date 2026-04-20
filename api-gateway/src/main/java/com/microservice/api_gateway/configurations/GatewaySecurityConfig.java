package com.microservice.api_gateway.configurations;
import java.beans.BeanProperty;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableWebFluxSecurity  //as we use spring cloud gateway, we need to use webflux security
public class GatewaySecurityConfig {

    // So your Gateway is doing TWO roles
    // 1. OAuth2 Client → handles login (Okta)
    // 2. Resource Server → validates JWT tokens on incoming requests
    
    @Bean
    public SecurityWebFilterChain getSecurityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("Security Config Loaded");    
            http
            .csrf().disable() // Disable CSRF protection (not needed for stateless APIs using JWT)
            .authorizeExchange() // Start defining authorization rules for incoming requests
            .anyExchange().authenticated()
            .and()
            .oauth2Login()
            .and()
            .oauth2Client()
            .and()
            .oauth2ResourceServer()
            .jwt();            
        return http.build();
    }

}
