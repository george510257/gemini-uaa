package com.gls.gemini.uaa.boot.authentication.customizer.server;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenIntrospectionEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义令牌验证端点
 */
@Component
public class TokenIntrospectionEndpointCustomizer implements Customizer<OAuth2TokenIntrospectionEndpointConfigurer> {

    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void customize(OAuth2TokenIntrospectionEndpointConfigurer configurer) {
        // 设置认证成功处理器
        configurer.introspectionResponseHandler(authenticationSuccessHandler);
        // 设置认证失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
