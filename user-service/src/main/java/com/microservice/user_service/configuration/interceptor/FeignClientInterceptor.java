package com.microservice.user_service.configuration.interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;

@Configuration  //use only if a bean is declared in this class, otherwise use @Component
@Component
public class FeignClientInterceptor implements RequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(FeignClientInterceptor.class);

    @Autowired
    private OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("UserService: Intercepting Feign client request to: " + requestTemplate.url());
        String token = manager.authorize(OAuth2AuthorizeRequest
                                        .withClientRegistrationId("my-internal-client")
                                        .principal("internal").build())
                                        .getAccessToken()
                                        .getTokenValue();
        requestTemplate.header("Authorization", "Bearer " + token);
    }

}
