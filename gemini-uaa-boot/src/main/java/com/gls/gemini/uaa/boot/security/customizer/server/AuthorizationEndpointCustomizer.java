package com.gls.gemini.uaa.boot.security.customizer.server;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义授权端点
 */
@Component
public class AuthorizationEndpointCustomizer implements Customizer<OAuth2AuthorizationEndpointConfigurer> {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制授权端点
     *
     * @param configurer OAuth2AuthorizationEndpointConfigurer 对象
     */
    @Override
    public void customize(OAuth2AuthorizationEndpointConfigurer configurer) {
        // 设置认证失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
