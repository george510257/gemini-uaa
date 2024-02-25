package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

/**
 * 授权请求定制
 */
@Component
public class AuthorizeHttpRequestsCustomizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
    /**
     * 自定义授权请求
     *
     * @param registry 授权请求配置器
     */
    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // 授权请求
        registry.requestMatchers("/auth/**", "/v3/api-docs").permitAll();
        // 所有请求都需要认证
        registry.anyRequest().authenticated();
    }
}
