package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 异常处理定制
 */
@Component
public class ExceptionHandlingCustomizer implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {
    /**
     * 自定义异常处理
     *
     * @param configurer 异常处理配置器
     */
    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
        // 配置异常处理 - Bearer Token 认证入口点
        configurer.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
        // 配置异常处理 - Bearer Token 访问拒绝处理器
        configurer.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
    }
}
