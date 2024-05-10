package com.gls.gemini.uaa.boot.authentication.customizer;

import com.gls.gemini.uaa.boot.authentication.customizer.server.*;
import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义OAuth2授权服务器
 */
@Component
public class OAuth2AuthorizationServerCustomizer implements Customizer<OAuth2AuthorizationServerConfigurer> {

    @Resource
    private OidcCustomizer oidcCustomizer;
    @Resource
    private AuthorizationEndpointCustomizer authorizationEndpointCustomizer;
    @Resource
    private DeviceAuthorizationEndpointCustomizer deviceAuthorizationEndpointCustomizer;
    @Resource
    private ClientAuthenticationCustomizer clientAuthenticationCustomizer;
    @Resource
    private TokenEndpointCustomizer tokenEndpointCustomizer;

    /**
     * 定制OAuth2授权服务器
     *
     * @param configurer OAuth2AuthorizationServerConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // 配置OIDC
        configurer.oidc(oidcCustomizer);
        // 配置授权端点
        configurer.authorizationEndpoint(authorizationEndpointCustomizer);
        // 配置设备授权端点
        configurer.deviceAuthorizationEndpoint(deviceAuthorizationEndpointCustomizer);
        // 配置客户端认证
        configurer.clientAuthentication(clientAuthenticationCustomizer);
        // 配置令牌端点
        configurer.tokenEndpoint(tokenEndpointCustomizer);
    }

}
