package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.*;

/**
 * 自定义OIDC配置
 */
public class OidcCustomizer implements Customizer<OidcConfigurer> {
    /**
     * 定制OIDC配置
     *
     * @param configurer OIDC配置对象
     */
    @Override
    public void customize(OidcConfigurer configurer) {
        // 配置用户信息端点
        configurer.userInfoEndpoint(this::userInfoEndpointCustomize);
        // 配置登出端点
        configurer.logoutEndpoint(this::logoutEndpointCustomize);
        // 配置提供者配置端点
        configurer.providerConfigurationEndpoint(this::providerConfigurationEndpointCustomize);
        // 配置客户端注册端点
        configurer.clientRegistrationEndpoint(this::clientRegistrationEndpointCustomize);
    }

    /**
     * 定制用户信息端点
     *
     * @param configurer OIDC用户信息端点配置对象
     */
    private void userInfoEndpointCustomize(OidcUserInfoEndpointConfigurer configurer) {
    }

    /**
     * 定制登出端点
     *
     * @param configurer OIDC登出端点配置对象
     */
    private void logoutEndpointCustomize(OidcLogoutEndpointConfigurer configurer) {
    }

    /**
     * 定制提供者配置端点
     *
     * @param configurer OIDC提供者配置端点配置对象
     */
    private void providerConfigurationEndpointCustomize(OidcProviderConfigurationEndpointConfigurer configurer) {
    }

    /**
     * 定制客户端注册端点
     *
     * @param configurer OIDC客户端注册端点配置对象
     */
    private void clientRegistrationEndpointCustomize(OidcClientRegistrationEndpointConfigurer configurer) {
    }
}
