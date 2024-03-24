package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义OAuth2授权服务器
 */
@Component
public class OAuth2AuthorizationServerCustomizer implements Customizer<OAuth2AuthorizationServerConfigurer> {
    /**
     * 定制OAuth2授权服务器
     *
     * @param configurer OAuth2AuthorizationServerConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // 配置OIDC
        configurer.oidc(this::oidcCustomize);
        // 配置授权端点
        configurer.authorizationEndpoint(this::authorizationEndpointCustomize);
        // 配置令牌端点
        configurer.tokenEndpoint(this::tokenEndpointCustomize);
    }

    /**
     * 令牌端点定制
     *
     * @param configurer OAuth2TokenEndpointConfigurer 对象
     */
    private void tokenEndpointCustomize(OAuth2TokenEndpointConfigurer configurer) {
    }

    /**
     * 授权端点定制
     *
     * @param configurer OAuth2AuthorizationEndpointConfigurer 对象
     */
    private void authorizationEndpointCustomize(OAuth2AuthorizationEndpointConfigurer configurer) {
    }

    /**
     * OIDC定制
     *
     * @param configurer OIDC配置器
     */
    private void oidcCustomize(OidcConfigurer configurer) {
    }
}
