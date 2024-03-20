package com.gls.gemini.uaa.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;

@Configuration
public class UaaJacksonConfig {

    @Bean
    @Order(10)
    public CoreJackson2Module coreJackson2Module() {
        return new CoreJackson2Module();
    }

    @Bean
    @Order(11)
    public OAuth2AuthorizationServerJackson2Module oAuth2AuthorizationServerJackson2Module() {
        return new OAuth2AuthorizationServerJackson2Module();
    }
}
