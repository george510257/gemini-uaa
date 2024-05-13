package com.gls.gemini.uaa.boot.security.customizer.server.oidc;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcProviderConfigurationEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.OidcProviderConfiguration;
import org.springframework.stereotype.Component;

/**
 * 提供者配置端点定制器
 */
@Component
public class ProviderConfigurationEndpointCustomizer implements Customizer<OidcProviderConfigurationEndpointConfigurer> {
    /**
     * 定制提供者配置端点
     *
     * @param configurer 提供者配置端点配置器
     */
    @Override
    public void customize(OidcProviderConfigurationEndpointConfigurer configurer) {
        // 定制提供者配置
        configurer.providerConfigurationCustomizer(this::customizeProviderConfiguration);
    }

    /**
     * 定制提供者配置
     *
     * @param builder 提供者配置构建器
     */
    private void customizeProviderConfiguration(OidcProviderConfiguration.Builder builder) {
    }
}
