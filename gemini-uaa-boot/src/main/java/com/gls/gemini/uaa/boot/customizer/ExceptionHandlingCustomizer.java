package com.gls.gemini.uaa.boot.customizer;

import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 自定义异常处理
 */
@Component
public class ExceptionHandlingCustomizer implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {
    /**
     * 定制异常处理
     *
     * @param configurer ExceptionHandlingConfigurer<HttpSecurity> 对象
     */
    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
        // 配置登录入口
        configurer.defaultAuthenticationEntryPointFor(
                new LoginUrlAuthenticationEntryPoint("/login"),
                new MediaTypeRequestMatcher(MediaType.TEXT_HTML));
    }

}
