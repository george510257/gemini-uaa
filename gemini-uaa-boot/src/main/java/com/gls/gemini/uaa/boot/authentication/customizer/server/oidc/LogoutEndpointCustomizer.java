package com.gls.gemini.uaa.boot.authentication.customizer.server.oidc;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcLogoutEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 登出端点定制器
 */
@Component
public class LogoutEndpointCustomizer implements Customizer<OidcLogoutEndpointConfigurer> {

    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制登出端点
     *
     * @param configurer 登出端点配置器
     */
    @Override
    public void customize(OidcLogoutEndpointConfigurer configurer) {
        // 设置登出响应处理器
        configurer.logoutResponseHandler(authenticationSuccessHandler);
        // 设置登出失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
