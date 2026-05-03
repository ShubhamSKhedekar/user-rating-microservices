package com.microservice.api_gateway.configurations;
import java.beans.BeanProperty;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.context.annotation.Bean;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;

import java.util.*;

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
            .jwt(jwt -> jwt.jwtAuthenticationConverter(
                new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter())
            ));            
        
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();

        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {

            Collection<GrantedAuthority> authorities = new ArrayList<>();

            // ✅ Add scopes
            JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
            authorities.addAll(scopeConverter.convert(jwt));

            // ✅ Add custom claim (myclaim)
            List<String> roles = jwt.getClaimAsStringList("myclaim");
            if (roles != null) {
                roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            }
            return authorities;
        });

        return jwtConverter;
    }
}
