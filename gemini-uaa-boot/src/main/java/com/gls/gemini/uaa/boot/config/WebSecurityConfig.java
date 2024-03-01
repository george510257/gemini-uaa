package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.uaa.boot.customizer.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 权限配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    /**
     * 授权请求定制
     */
    @Resource
    private AuthorizeHttpRequestsCustomizer authorizeHttpRequestsCustomizer;
    /**
     * 异常处理定制
     */
    @Resource
    private ExceptionHandlingCustomizer exceptionHandlingCustomizer;
    /**
     * OAuth2授权服务器定制
     */
    @Resource
    private OAuth2AuthorizationServerCustomizer oauth2AuthorizationServerCustomizer;
    /**
     * OAuth2资源服务器定制
     */
    @Resource
    private OAuth2ResourceServerCustomizer oauth2ResourceServerCustomizer;
    /**
     * 会话管理定制
     */
    @Resource
    private SessionManagementCustomizer sessionManagementCustomizer;

    /**
     * 默认安全过滤链
     *
     * @param http HTTP安全
     * @return 安全过滤链
     * @throws Exception 异常
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());
        http.oauth2ResourceServer(oauth2ResourceServerCustomizer);
        http.sessionManagement(sessionManagementCustomizer);
        http.exceptionHandling(exceptionHandlingCustomizer);
        return http.build();
    }

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
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = http.getConfigurer(OAuth2AuthorizationServerConfigurer.class);
        http.with(authorizationServerConfigurer, oauth2AuthorizationServerCustomizer);
        return http.build();
    }

//    /**
//     * 授权服务器设置
//     *
//     * @return 授权服务器设置
//     */
//    @Bean
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder().build();
//    }

}
