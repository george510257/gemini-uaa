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
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * 权限配置
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Resource
    private AuthorizeHttpRequestsCustomizer authorizeHttpRequestsCustomizer;
    @Resource
    private CsrfCustomizer csrfCustomizer;
    @Resource
    private ExceptionHandlingCustomizer exceptionHandlingCustomizer;
    @Resource
    private HttpBasicCustomizer httpBasicCustomizer;
    @Resource
    private OAuth2AuthorizationServerCustomizer oauth2AuthorizationServerCustomizer;
    @Resource
    private OAuth2ResourceServerCustomizer oauth2ResourceServerCustomizer;
    @Resource
    private SessionManagementCustomizer sessionManagementCustomizer;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        http.csrf(csrfCustomizer);
        http.httpBasic(httpBasicCustomizer);
        http.formLogin(Customizer.withDefaults());
        http.oauth2ResourceServer(oauth2ResourceServerCustomizer);
        http.sessionManagement(sessionManagementCustomizer);
        http.exceptionHandling(exceptionHandlingCustomizer);
        return http.build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.securityMatcher(endpointsMatcher);
        http.authorizeHttpRequests(authorizeHttpRequestsCustomizer);
        http.csrf(csrfCustomizer);
        http.oauth2ResourceServer(oauth2ResourceServerCustomizer);
        http.exceptionHandling(exceptionHandlingCustomizer);
        http.with(authorizationServerConfigurer, oauth2AuthorizationServerCustomizer);
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}
