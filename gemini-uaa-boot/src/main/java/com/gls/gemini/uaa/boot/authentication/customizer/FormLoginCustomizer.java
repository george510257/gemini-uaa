package com.gls.gemini.uaa.boot.authentication.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;

/**
 * 自定义表单登录
 */
public class FormLoginCustomizer implements Customizer<FormLoginConfigurer<HttpSecurity>> {
    /**
     * 定制表单登录
     *
     * @param configurer FormLoginConfigurer<HttpSecurity> 对象
     */
    @Override
    public void customize(FormLoginConfigurer<HttpSecurity> configurer) {
        // 配置登录页面
//        configurer.loginPage("/login");
    }
}
