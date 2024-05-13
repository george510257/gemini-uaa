package com.gls.gemini.uaa.boot.authentication.customizer.server.oidc;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcProviderConfigurationEndpointConfigurer;

/**
 * 提供者配置端点定制器
 */
public class ProviderConfigurationEndpointCustomizer implements Customizer<OidcProviderConfigurationEndpointConfigurer> {
    @Override
    public void customize(OidcProviderConfigurationEndpointConfigurer configurer) {

    }
}
