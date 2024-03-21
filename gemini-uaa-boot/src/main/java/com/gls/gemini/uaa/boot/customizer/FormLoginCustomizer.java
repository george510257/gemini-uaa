package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义表单登录
 */
@Component
public class FormLoginCustomizer implements Customizer<FormLoginConfigurer<HttpSecurity>> {
    @Override
    public void customize(FormLoginConfigurer<HttpSecurity> configurer) {
        // 配置登录页面
//        configurer.loginPage("/login");
    }
}
