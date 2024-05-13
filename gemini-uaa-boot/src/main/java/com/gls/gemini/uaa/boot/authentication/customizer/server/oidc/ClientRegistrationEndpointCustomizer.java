package com.gls.gemini.uaa.boot.authentication.customizer.server.oidc;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcClientRegistrationEndpointConfigurer;

/**
 * 客户端注册端点定制器
 */
public class ClientRegistrationEndpointCustomizer implements Customizer<OidcClientRegistrationEndpointConfigurer> {
    @Override
    public void customize(OidcClientRegistrationEndpointConfigurer configurer) {

    }
}
