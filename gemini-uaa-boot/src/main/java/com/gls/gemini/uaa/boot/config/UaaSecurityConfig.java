package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.uaa.boot.authentication.customizer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                          AuthorizeHttpRequestsCustomizer authorizeHttpRequestsCustomizer,
                                                          FormLoginCustomizer formLoginCustomizer,
                                                          OAuth2ResourceServerCustomizer oauth2ResourceServerCustomizer,
                                                          CsrfCustomizer csrfCustomizer) throws Exception {
        // 请求授权
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        // 表单登录
        http.formLogin(formLoginCustomizer);
        // OAuth2资源服务器
        http.oauth2ResourceServer(oauth2ResourceServerCustomizer);
        // 关闭csrf
        http.csrf(csrfCustomizer);
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
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
                                                                      OAuth2AuthorizationServerCustomizer oauth2AuthorizationServerCustomizer,
                                                                      AuthorizeHttpRequestsCustomizer authorizeHttpRequestsCustomizer,
                                                                      ExceptionHandlingCustomizer exceptionHandlingCustomizer,
                                                                      CsrfCustomizer csrfCustomizer) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        // 安全匹配
        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher());
        // OAuth2认证服务器
        http.with(authorizationServerConfigurer, oauth2AuthorizationServerCustomizer);
        // 请求授权
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        // 异常处理
        http.exceptionHandling(exceptionHandlingCustomizer);
        // 关闭csrf
        http.csrf(csrfCustomizer);
        // 返回
        return http.build();
    }

    /**
     * 认证管理器
     *
     * @param authenticationConfiguration 认证配置
     * @return 认证管理器
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
