package com.microservice.api_gateway.controllers;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.api_gateway.models.AuthResponse;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@RestController
@RequestMapping("/auth")
// @EnableWebFluxSecurity
public class AuthController {

        private Logger logger = org.slf4j.LoggerFactory.getLogger(AuthController.class);

            @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
        @AuthenticationPrincipal OidcUser oidcUser,
        @AuthenticationPrincipal Jwt jwt,
        Model model
    )
    {
        AuthResponse authResponse = null;
        try {
            logger.info("User {} logged in with email {}", oidcUser.getName(), oidcUser.getEmail());

            authResponse = new AuthResponse();
            authResponse.setAccessToken(authorizedClient.getAccessToken().getTokenValue());
            authResponse.setRefreshToken(authorizedClient.getRefreshToken().getTokenValue());
            authResponse.setExpiresAt(authorizedClient.getAccessToken().getExpiresAt().toString());
            // authResponse.setEmail(oidcUser.getEmail());
            // authResponse.setName(oidcUser.getName());
            authResponse.setAuthorities(oidcUser.getAuthorities().stream().map(a -> a.getAuthority()).toList());
            // authResponse.setJwtToken(jwt.getTokenValue());
            authResponse.setUserId(oidcUser.getEmail()); // Assuming email is used as user ID
            
            return ResponseEntity.status(200).body(authResponse);
        } catch (Exception e) {
            logger.error("Login failed: {}", e.getMessage());
            return ResponseEntity.status(500).body(new AuthResponse());
        }        
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }
}
