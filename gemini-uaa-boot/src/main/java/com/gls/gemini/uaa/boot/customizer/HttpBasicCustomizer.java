package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.stereotype.Component;

/**
 * HTTP Basic 定制
 */
@Component
public class HttpBasicCustomizer implements Customizer<HttpBasicConfigurer<HttpSecurity>> {
    /**
     * 自定义 HTTP Basic
     *
     * @param configurer HTTP Basic 配置器
     */
    @Override
    public void customize(HttpBasicConfigurer<HttpSecurity> configurer) {
    }
}
