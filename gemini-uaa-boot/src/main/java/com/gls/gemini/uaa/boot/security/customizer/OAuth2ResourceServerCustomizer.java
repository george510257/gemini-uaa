package com.gls.gemini.uaa.boot.security.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义OAuth2资源服务器
 */
@Component
public class OAuth2ResourceServerCustomizer implements Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> {
    /**
     * 定制OAuth2资源服务器
     *
     * @param configurer OAuth2ResourceServerConfigurer<HttpSecurity> 对象
     */
    @Override
    public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        // 配置JWT
        configurer.jwt(this::jwtCustomize);
    }

    /**
     * JWT定制
     *
     * @param configurer JwtConfigurer 对象
     */
    private void jwtCustomize(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer configurer) {
    }
}
