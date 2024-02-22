package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

/**
 * 会话管理定制
 */
@Component
public class SessionManagementCustomizer implements Customizer<SessionManagementConfigurer<HttpSecurity>> {
    /**
     * 自定义会话管理
     *
     * @param configurer 会话管理配置器
     */
    @Override
    public void customize(SessionManagementConfigurer<HttpSecurity> configurer) {
        // 会话创建策略 - 禁用
        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
