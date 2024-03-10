package com.gls.gemini.uaa.boot.config;

import com.gls.gemini.uaa.boot.constants.UaaConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

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
        // 配置OAuth2授权服务器
        http.with(new OAuth2AuthorizationServerConfigurer(), this::oauth2AuthorizationServerCustomizer);
        // 配置异常处理
        http.exceptionHandling(this::exceptionHandlingCustomizer);
        // 配置资源服务器
        http.oauth2ResourceServer(this::oauth2ResourceServerCustomizer);
        // 配置CSRF
        http.csrf(this::csrfCustomizer);
        // 配置授权请求
        http.authorizeHttpRequests(this::authorizeHttpRequestsCustomizer);
        // 配置表单登录
        http.formLogin(this::formLoginCustomizer);
        // 返回安全过滤链
        return http.build();
    }

    private void formLoginCustomizer(FormLoginConfigurer<HttpSecurity> configurer) {
        configurer.loginPage(UaaConstants.LOGIN_PAGE);
    }

    private void authorizeHttpRequestsCustomizer(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        // 配置静态资源
        registry.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll();
        // 配置授权请求
        registry.requestMatchers(UaaConstants.LOGIN_PAGE, UaaConstants.CONSENT_PAGE).permitAll();
        // 其他请求需要认证
        registry.anyRequest().authenticated();
    }

    private void csrfCustomizer(CsrfConfigurer<HttpSecurity> configurer) {
        configurer.disable();
    }

    private void oauth2ResourceServerCustomizer(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        // 配置资源服务器
        configurer.jwt(Customizer.withDefaults());
    }

    private void exceptionHandlingCustomizer(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
        // 当未登录时访问认证端点时重定向至login页面
        configurer.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint(UaaConstants.LOGIN_PAGE),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML));
    }

    private void oauth2AuthorizationServerCustomizer(OAuth2AuthorizationServerConfigurer configurer) {
        // 开启OpenID Connect 1.0协议相关端点
        configurer.oidc(Customizer.withDefaults());
        // 设置自定义用户确认授权页
        configurer.authorizationEndpoint(authorizationEndpoint -> authorizationEndpoint.consentPage(UaaConstants.CONSENT_PAGE));
    }

}
