package com.gls.gemini.uaa.boot.authentication.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义请求授权
 */
@Component
public class AuthorizeHttpRequestsCustomizer implements Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> {
    /**
     * 定制请求授权
     *
     * @param registry AuthorizationManagerRequestMatcherRegistry 对象
     */
    @Override
    public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // 配置白名单
        registry.requestMatchers("/actuator/**", "/v3/api-docs", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**").permitAll();
        // 所有请求都需要认证
        registry.anyRequest().authenticated();
    }
}
