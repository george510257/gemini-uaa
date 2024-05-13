package com.gls.gemini.uaa.boot.security.customizer.server;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenRevocationEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义令牌撤销端点
 */
@Component
public class TokenRevocationEndpointCustomizer implements Customizer<OAuth2TokenRevocationEndpointConfigurer> {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void customize(OAuth2TokenRevocationEndpointConfigurer configurer) {
        // 设置认证失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
