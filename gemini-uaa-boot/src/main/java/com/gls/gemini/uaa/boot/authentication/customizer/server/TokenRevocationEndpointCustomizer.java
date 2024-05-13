package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenRevocationEndpointConfigurer;

/**
 * 自定义令牌撤销端点
 */
public class TokenRevocationEndpointCustomizer implements Customizer<OAuth2TokenRevocationEndpointConfigurer> {
    @Override
    public void customize(OAuth2TokenRevocationEndpointConfigurer configurer) {

    }
}
