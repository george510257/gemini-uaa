package com.gls.gemini.uaa.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 权限配置
 */
@Configuration
public class AuthorizationServerConfig {

    /**
     * 授权服务器安全过滤链
     *
     * @param http HTTP安全
     * @return 安全过滤链
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http.with(authorizationServerConfigurer, this::oauth2AuthorizationServerCustomizer);
        return http.build();
    }

    private void formLoginCustomizer(FormLoginConfigurer<HttpSecurity> configurer) {
    }

    private void oauth2AuthorizationServerCustomizer(OAuth2AuthorizationServerConfigurer configurer) {
        // 个性化oidc
        configurer.oidc(Customizer.withDefaults());
    }

}
