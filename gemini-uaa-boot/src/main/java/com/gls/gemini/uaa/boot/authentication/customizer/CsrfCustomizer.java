package com.gls.gemini.uaa.boot.authentication.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;

/**
 * 自定义 CSRF 防护
 */
public class CsrfCustomizer implements Customizer<CsrfConfigurer<HttpSecurity>> {
    @Override
    public void customize(CsrfConfigurer<HttpSecurity> configurer) {
        // 关闭 CSRF 防护
        configurer.disable();
    }
}
