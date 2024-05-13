package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerMetadataEndpointConfigurer;

/**
 * 自定义授权源端点
 */
public class AuthorizationServerMetadataEndpointCustomizer implements Customizer<OAuth2AuthorizationServerMetadataEndpointConfigurer> {
    @Override
    public void customize(OAuth2AuthorizationServerMetadataEndpointConfigurer configurer) {

    }
}
