package com.gls.gemini.uaa.boot.authentication.customizer.server;

import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.ClientRegistrationEndpointCustomizer;
import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.LogoutEndpointCustomizer;
import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.ProviderConfigurationEndpointCustomizer;
import com.gls.gemini.uaa.boot.authentication.customizer.server.oidc.UserInfoEndpointCustomizer;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcConfigurer;

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
        // 定制客户端注册端点
        configurer.clientRegistrationEndpoint(new ClientRegistrationEndpointCustomizer());
        // 定制登出端点
        configurer.logoutEndpoint(new LogoutEndpointCustomizer());
        // 定制提供者配置端点
        configurer.providerConfigurationEndpoint(new ProviderConfigurationEndpointCustomizer());
        // 定制用户信息端点
        configurer.userInfoEndpoint(new UserInfoEndpointCustomizer());
    }

}
