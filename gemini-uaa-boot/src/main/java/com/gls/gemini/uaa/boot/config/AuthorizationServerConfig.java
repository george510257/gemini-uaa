package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.uaa.boot.converter.CustomAccessTokenRequestConverter;
import com.gls.gemini.uaa.boot.handler.CustomAccessTokenResponseHandler;
import com.gls.gemini.uaa.boot.handler.CustomErrorResponseHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2ClientAuthenticationConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 权限配置
 */
@Configuration
public class AuthorizationServerConfig {
    @Resource
    private OAuth2AuthorizationService authorizationService;

    /**
     * 授权服务器安全过滤链
     *
     * @param http HTTP安全
     * @return 安全过滤链
     * @throws Exception 异常
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http.with(authorizationServerConfigurer, this::oauth2AuthorizationServerCustomizer);
        return http.build();
    }

    private void oauth2AuthorizationServerCustomizer(OAuth2AuthorizationServerConfigurer configurer) {
        // 个性化oidc
        configurer.oidc(Customizer.withDefaults());
        // 个性化token端点
        configurer.tokenEndpoint(this::tokenEndpointCustomizer);
        // 个性化客户端认证
        configurer.clientAuthentication(this::clientAuthenticationCustomizer);
        // 个性化授权端点
        configurer.authorizationEndpoint(this::authorizationEndpointCustomizer);
        // 个性化授权服务
        configurer.authorizationService(authorizationService);

    }

    private void authorizationEndpointCustomizer(OAuth2AuthorizationEndpointConfigurer configurer) {
        // 个性化同意页面
        configurer.consentPage("/oauth2/consent");
    }

    private void clientAuthenticationCustomizer(OAuth2ClientAuthenticationConfigurer configurer) {
        // 处理客户端认证异常
        configurer.errorResponseHandler(new CustomErrorResponseHandler());
    }

    private void tokenEndpointCustomizer(OAuth2TokenEndpointConfigurer configurer) {
        // 注入自定义的授权认证Converter
        configurer.accessTokenRequestConverter(new CustomAccessTokenRequestConverter());
        // 登录成功处理器
        configurer.accessTokenResponseHandler(new CustomAccessTokenResponseHandler());
        // 登录失败处理器
        configurer.errorResponseHandler(new CustomErrorResponseHandler());
    }

}
