package com.gls.gemini.uaa.boot.authentication.customizer;

import com.gls.gemini.uaa.boot.authentication.password.OAuth2PasswordAuthenticationConverter;
import com.gls.gemini.uaa.boot.authentication.password.OAuth2PasswordAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.*;
import org.springframework.security.web.authentication.AuthenticationConverter;

import java.util.List;

/**
 * 自定义OAuth2授权服务器
 */
public class OAuth2AuthorizationServerCustomizer implements Customizer<OAuth2AuthorizationServerConfigurer> {
    /**
     * HttpSecurity对象
     */
    private final HttpSecurity httpSecurity;

    public OAuth2AuthorizationServerCustomizer(HttpSecurity httpSecurity) {
        this.httpSecurity = httpSecurity;
    }

    /**
     * 定制OAuth2授权服务器
     *
     * @param configurer OAuth2AuthorizationServerConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // 配置OIDC
        configurer.oidc(this::oidcCustomize);
        // 配置授权端点
        configurer.authorizationEndpoint(this::authorizationEndpointCustomize);
        // 配置设备授权端点
        configurer.deviceAuthorizationEndpoint(this::deviceAuthorizationEndpointCustomize);
        // 配置客户端认证
        configurer.clientAuthentication(this::clientAuthenticationCustomize);
        // 配置令牌端点
        configurer.tokenEndpoint(this::tokenEndpointCustomize);
    }

    /**
     * 客户端认证定制
     *
     * @param configurer OAuth2ClientAuthenticationConfigurer 对象
     */
    private void clientAuthenticationCustomize(OAuth2ClientAuthenticationConfigurer configurer) {
    }

    /**
     * 设备授权端点定制
     *
     * @param configurer OAuth2DeviceAuthorizationEndpointConfigurer 对象
     */
    private void deviceAuthorizationEndpointCustomize(OAuth2DeviceAuthorizationEndpointConfigurer configurer) {
        // 配置认证提供者
        configurer.authenticationProviders(this::deviceAuthenticationProvidersCustomize);
        // 配置授权请求转换器
        configurer.deviceAuthorizationRequestConverters(this::deviceAuthorizationRequestConvertersCustomize);
    }

    /**
     * 设备认证提供者定制
     *
     * @param authenticationProviders 认证提供者列表
     */
    private void deviceAuthenticationProvidersCustomize(List<AuthenticationProvider> authenticationProviders) {
    }

    /**
     * 设备授权请求转换器定制
     *
     * @param authenticationConverters 授权请求转换器列表
     */
    private void deviceAuthorizationRequestConvertersCustomize(List<AuthenticationConverter> authenticationConverters) {
    }

    /**
     * 令牌端点定制
     *
     * @param configurer OAuth2TokenEndpointConfigurer 对象
     */
    private void tokenEndpointCustomize(OAuth2TokenEndpointConfigurer configurer) {
        // 配置认证提供者
        configurer.authenticationProviders(this::tokenAuthenticationProvidersCustomize);
        // 配置访问令牌请求转换器
        configurer.accessTokenRequestConverters(this::accessTokenRequestConvertersCustomize);
    }

    /**
     * 访问令牌认证提供者定制
     *
     * @param authenticationProviders 认证提供者列表
     */
    private void tokenAuthenticationProvidersCustomize(List<AuthenticationProvider> authenticationProviders) {
        // 创建OAuth2密码认证提供者
        OAuth2PasswordAuthenticationProvider provider = new OAuth2PasswordAuthenticationProvider(httpSecurity);
        authenticationProviders.add(provider);
    }

    /**
     * 访问令牌请求转换器定制
     *
     * @param authenticationConverters 访问令牌请求转换器列表
     */
    private void accessTokenRequestConvertersCustomize(List<AuthenticationConverter> authenticationConverters) {
        // 添加OAuth2密码认证转换器
        authenticationConverters.add(new OAuth2PasswordAuthenticationConverter());
    }

    /**
     * 授权端点定制
     *
     * @param configurer OAuth2AuthorizationEndpointConfigurer 对象
     */
    private void authorizationEndpointCustomize(OAuth2AuthorizationEndpointConfigurer configurer) {
        // 配置授权请求转换器
        configurer.authorizationRequestConverters(this::authorizationRequestConvertersCustomize);
        // 配置认证提供者
        configurer.authenticationProviders(this::authenticationProvidersCustomize);
    }

    /**
     * 认证提供者定制
     *
     * @param authenticationProviders 认证提供者列表
     */
    private void authenticationProvidersCustomize(List<AuthenticationProvider> authenticationProviders) {
    }

    /**
     * 授权请求转换器定制
     *
     * @param authenticationConverters 授权请求转换器列表
     */
    private void authorizationRequestConvertersCustomize(List<AuthenticationConverter> authenticationConverters) {
    }

    /**
     * OIDC定制
     *
     * @param configurer OIDC配置器
     */
    private void oidcCustomize(OidcConfigurer configurer) {
        // 配置用户信息端点
        configurer.userInfoEndpoint(this::userInfoEndpointCustomize);
        // 配置登出端点
        configurer.logoutEndpoint(this::logoutEndpointCustomize);
        // 配置提供者配置端点
        configurer.providerConfigurationEndpoint(this::providerConfigurationEndpointCustomize);
        // 配置客户端注册端点
        configurer.clientRegistrationEndpoint(this::clientRegistrationEndpointCustomize);
    }

    /**
     * 客户端注册端点定制
     *
     * @param configurer OIDC客户端注册端点配置器
     */
    private void clientRegistrationEndpointCustomize(OidcClientRegistrationEndpointConfigurer configurer) {
    }

    /**
     * 提供者配置端点定制
     *
     * @param configurer OIDC提供者配置端点配置器
     */
    private void providerConfigurationEndpointCustomize(OidcProviderConfigurationEndpointConfigurer configurer) {
    }

    /**
     * 登出端点定制
     *
     * @param configurer OIDC登出端点配置器
     */
    private void logoutEndpointCustomize(OidcLogoutEndpointConfigurer configurer) {
    }

    /**
     * 用户信息端点定制
     *
     * @param configurer OIDC用户信息端点配置器
     */
    private void userInfoEndpointCustomize(OidcUserInfoEndpointConfigurer configurer) {
    }
}
