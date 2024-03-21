package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.uaa.boot.customizer.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * UAA安全配置
 */
@Configuration
public class UaaSecurityConfig {

    /**
     * 请求授权
     */
    @Resource
    private AuthorizeHttpRequestsCustomizer authorizeHttpRequestsCustomizer;
    /**
     * 表单登录
     */
    @Resource
    private FormLoginCustomizer formLoginCustomizer;
    /**
     * OAuth2认证服务器
     */
    @Resource
    private OAuth2AuthorizationServerCustomizer oauth2AuthorizationServerCustomizer;
    /**
     * 异常处理
     */
    @Resource
    private ExceptionHandlingCustomizer exceptionHandlingCustomizer;
    /**
     * OAuth2资源服务器
     */
    @Resource
    private OAuth2ResourceServerCustomizer oauth2ResourceServerCustomizer;

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
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        // 表单登录
        http.formLogin(formLoginCustomizer);
        // 关闭csrf
        http.csrf(AbstractHttpConfigurer::disable);
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
        http.with(authorizationServerConfigurer, oauth2AuthorizationServerCustomizer);
        // OAuth2资源服务器
        http.oauth2ResourceServer(oauth2ResourceServerCustomizer);
        // 请求授权
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        // 关闭csrf
        http.csrf(AbstractHttpConfigurer::disable);
        // 异常处理
        http.exceptionHandling(exceptionHandlingCustomizer);
        // 返回
        return http.build();
    }
}
