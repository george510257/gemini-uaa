package com.gls.gemini.uaa.boot.security.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationEndpointConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义授权端点
 */
@Component
public class AuthorizationEndpointCustomizer implements Customizer<OAuth2AuthorizationEndpointConfigurer> {

    /**
     * 定制授权端点
     *
     * @param configurer OAuth2AuthorizationEndpointConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationEndpointConfigurer configurer) {
    }
}
