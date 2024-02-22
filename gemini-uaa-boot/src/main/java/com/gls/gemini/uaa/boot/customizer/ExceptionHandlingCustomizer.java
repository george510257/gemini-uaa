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
        configurer
                // 自定义认证入口点 (401) 处理器
                .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                // 自定义拒绝访问处理器 (403) 处理器
                .accessDeniedHandler(new BearerTokenAccessDeniedHandler());
    }
}
