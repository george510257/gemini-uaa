package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.starter.security.servlet.customizer.AuthorizeHttpRequestsCustomizer;
import com.gls.gemini.starter.security.servlet.customizer.CsrfCustomizer;
import com.gls.gemini.starter.security.servlet.customizer.ExceptionHandlingCustomizer;
import com.gls.gemini.uaa.boot.security.customizer.OAuth2AuthorizationServerCustomizer;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * UAA安全配置
 */
@Configuration
public class UaaSecurityConfig {
    @Resource
    private AuthorizeHttpRequestsCustomizer authorizeHttpRequestsCustomizer;
    @Resource
    private CsrfCustomizer csrfCustomizer;
    @Resource
    private ExceptionHandlingCustomizer exceptionHandlingCustomizer;
    @Resource
    private OAuth2AuthorizationServerCustomizer authorizationServerCustomizer;

    /**
     * 认证服务器安全过滤器链
     *
     * @param http HttpSecurity 对象
     * @return SecurityFilterChain 对象
     * @throws Exception 异常
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        // 安全匹配
        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher());
        // OAuth2认证服务器
        http.with(authorizationServerConfigurer, authorizationServerCustomizer);
        // 请求授权
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        // 异常处理
        http.exceptionHandling(exceptionHandlingCustomizer);
        // 关闭csrf
        http.csrf(csrfCustomizer);
        // 返回
        return http.build();
    }

}
