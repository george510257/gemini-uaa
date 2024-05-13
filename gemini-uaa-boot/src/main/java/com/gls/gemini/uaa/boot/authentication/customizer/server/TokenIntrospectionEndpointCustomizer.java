package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenIntrospectionEndpointConfigurer;

/**
 * 自定义令牌验证端点
 */
public class TokenIntrospectionEndpointCustomizer implements Customizer<OAuth2TokenIntrospectionEndpointConfigurer> {
    @Override
    public void customize(OAuth2TokenIntrospectionEndpointConfigurer configurer) {

    }
}
