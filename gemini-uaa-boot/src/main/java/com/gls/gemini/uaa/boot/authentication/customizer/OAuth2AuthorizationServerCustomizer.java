package com.gls.gemini.uaa.boot.authentication.customizer;

import com.gls.gemini.uaa.boot.authentication.customizer.server.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;

/**
 * 自定义OAuth2授权服务器
 */
@RequiredArgsConstructor
public class OAuth2AuthorizationServerCustomizer implements Customizer<OAuth2AuthorizationServerConfigurer> {

    private final HttpSecurity httpSecurity;

    /**
     * 定制OAuth2授权服务器
     *
     * @param configurer OAuth2AuthorizationServerConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // 配置授权端点
        configurer.authorizationEndpoint(new AuthorizationEndpointCustomizer());
        // 配置授权源端点
        configurer.authorizationServerMetadataEndpoint(new AuthorizationServerMetadataEndpointCustomizer());
        // 配置客户端认证
        configurer.clientAuthentication(new ClientAuthenticationCustomizer());
        // 配置设备授权端点
        configurer.deviceAuthorizationEndpoint(new DeviceAuthorizationEndpointCustomizer());
        // 配置设备验证端点
        configurer.deviceVerificationEndpoint(new DeviceVerificationEndpointCustomizer());
        // 配置OIDC
        configurer.oidc(new OidcCustomizer());
        // 配置令牌端点
        configurer.tokenEndpoint(new TokenEndpointCustomizer(httpSecurity));
        // 配置令牌解析端点
        configurer.tokenIntrospectionEndpoint(new TokenIntrospectionEndpointCustomizer());
        // 配置令牌撤销端点
        configurer.tokenRevocationEndpoint(new TokenRevocationEndpointCustomizer());
    }

}
