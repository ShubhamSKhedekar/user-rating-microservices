package com.microservice.api_gateway.models;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private String expiresAt;
    private Collection<String> authorities;
}
