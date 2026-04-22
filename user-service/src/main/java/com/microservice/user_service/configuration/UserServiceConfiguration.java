package com.microservice.user_service.configuration;

import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import com.microservice.user_service.configuration.interceptor.RestTemplateInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;

@Configuration
public class UserServiceConfiguration {


    @Autowired
    private OAuth2AuthorizedClientManager manager;

    // @Bean
    // @LoadBalanced
    // public RestTemplate getRestTemplate(){
    //     return new RestTemplate();
    // }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // Add interceptors or other customizations to the RestTemplate here if needed
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors != null) {
            interceptors.add(new RestTemplateInterceptor(manager));
            restTemplate.setInterceptors(interceptors);
        }
        
        return restTemplate;
    }
}
