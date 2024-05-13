package com.gls.gemini.uaa.boot.security.customizer.server.oidc;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcUserInfoEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 用户信息端点定制器
 */
@Component
public class UserInfoEndpointCustomizer implements Customizer<OidcUserInfoEndpointConfigurer> {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制用户信息端点
     *
     * @param configurer 用户信息端点配置器
     */
    @Override
    public void customize(OidcUserInfoEndpointConfigurer configurer) {
        // 设置用户信息失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
