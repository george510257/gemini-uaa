package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.stereotype.Component;

/**
 * CSRF 定制
 */
@Component
public class CsrfCustomizer implements Customizer<CsrfConfigurer<HttpSecurity>> {
    /**
     * 自定义 CSRF
     *
     * @param configurer CSRF 配置器
     */
    @Override
    public void customize(CsrfConfigurer<HttpSecurity> configurer) {
        // 禁用 CSRF
        configurer.disable();
    }
}
