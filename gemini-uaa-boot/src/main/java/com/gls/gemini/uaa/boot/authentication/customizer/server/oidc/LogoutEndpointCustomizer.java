package com.gls.gemini.uaa.boot.authentication.customizer.server.oidc;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcLogoutEndpointConfigurer;

/**
 * 登出端点定制器
 */
public class LogoutEndpointCustomizer implements Customizer<OidcLogoutEndpointConfigurer> {
    @Override
    public void customize(OidcLogoutEndpointConfigurer configurer) {

    }
}
