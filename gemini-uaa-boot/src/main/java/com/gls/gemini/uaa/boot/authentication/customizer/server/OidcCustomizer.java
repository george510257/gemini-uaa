package com.gls.gemini.uaa.boot.authentication.customizer.server;

import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.ClientRegistrationEndpointCustomizer;
import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.LogoutEndpointCustomizer;
import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.ProviderConfigurationEndpointCustomizer;
import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.UserInfoEndpointCustomizer;
import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义OIDC配置
 */
@Component
public class OidcCustomizer implements Customizer<OidcConfigurer> {

    @Resource
    private ClientRegistrationEndpointCustomizer clientRegistrationEndpointCustomizer;
    @Resource
    private LogoutEndpointCustomizer logoutEndpointCustomizer;
    @Resource
    private ProviderConfigurationEndpointCustomizer providerConfigurationEndpointCustomizer;
    @Resource
    private UserInfoEndpointCustomizer userInfoEndpointCustomizer;

    /**
     * 定制OIDC配置
     *
     * @param configurer OIDC配置对象
     */
    @Override
    public void customize(OidcConfigurer configurer) {
        // 定制客户端注册端点
        configurer.clientRegistrationEndpoint(clientRegistrationEndpointCustomizer);
        // 定制登出端点
        configurer.logoutEndpoint(logoutEndpointCustomizer);
        // 定制提供者配置端点
        configurer.providerConfigurationEndpoint(providerConfigurationEndpointCustomizer);
        // 定制用户信息端点
        configurer.userInfoEndpoint(userInfoEndpointCustomizer);
    }

}
