package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.uaa.boot.authentication.customizer.*;
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

    /**
     * 默认安全过滤器链
     *
     * @param http HttpSecurity 对象
     * @return SecurityFilterChain 对象
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // 请求授权
        http.authorizeHttpRequests(new AuthorizeHttpRequestsCustomizer());
        // 表单登录
        http.formLogin(new FormLoginCustomizer());
        // OAuth2资源服务器
        http.oauth2ResourceServer(new OAuth2ResourceServerCustomizer());
        // 关闭csrf
        http.csrf(new CsrfCustomizer());
        return http.build();
    }

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
        http.with(authorizationServerConfigurer, new OAuth2AuthorizationServerCustomizer(http));
        // 请求授权
        http.authorizeHttpRequests(new AuthorizeHttpRequestsCustomizer());
        // 异常处理
        http.exceptionHandling(new ExceptionHandlingCustomizer());
        // 关闭csrf
        http.csrf(new CsrfCustomizer());
        // 返回
        return http.build();
    }

}
