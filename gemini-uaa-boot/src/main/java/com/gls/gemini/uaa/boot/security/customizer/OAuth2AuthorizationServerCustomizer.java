package com.gls.gemini.uaa.boot.security.customizer;

import com.gls.gemini.uaa.boot.security.customizer.server.*;
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
    private AuthorizationEndpointCustomizer authorizationEndpointCustomizer;
    @Resource
    private AuthorizationServerMetadataEndpointCustomizer authorizationServerMetadataEndpointCustomizer;
    @Resource
    private ClientAuthenticationCustomizer clientAuthenticationCustomizer;
    @Resource
    private DeviceAuthorizationEndpointCustomizer deviceAuthorizationEndpointCustomizer;
    @Resource
    private DeviceVerificationEndpointCustomizer deviceVerificationEndpointCustomizer;
    @Resource
    private OidcCustomizer oidcCustomizer;
    @Resource
    private TokenEndpointCustomizer tokenEndpointCustomizer;
    @Resource
    private TokenIntrospectionEndpointCustomizer tokenIntrospectionEndpointCustomizer;
    @Resource
    private TokenRevocationEndpointCustomizer tokenRevocationEndpointCustomizer;

    /**
     * 定制OAuth2授权服务器
     *
     * @param configurer OAuth2AuthorizationServerConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // 定制授权端点
        configurer.authorizationEndpoint(authorizationEndpointCustomizer);
        // 定制授权服务器元数据端点
        configurer.authorizationServerMetadataEndpoint(authorizationServerMetadataEndpointCustomizer);
        // 定制OAuth2客户端认证
        configurer.clientAuthentication(clientAuthenticationCustomizer);
        // 定制设备授权端点
        configurer.deviceAuthorizationEndpoint(deviceAuthorizationEndpointCustomizer);
        // 定制设备验证端点
        configurer.deviceVerificationEndpoint(deviceVerificationEndpointCustomizer);
        // 定制OpenID Connect
        configurer.oidc(oidcCustomizer);
        // 定制令牌端点
        configurer.tokenEndpoint(tokenEndpointCustomizer);
        // 定制令牌验证端点
        configurer.tokenIntrospectionEndpoint(tokenIntrospectionEndpointCustomizer);
        // 定制令牌撤销端点
        configurer.tokenRevocationEndpoint(tokenRevocationEndpointCustomizer);
    }

}
