package com.gls.gemini.uaa.boot.authentication.customizer.server;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2DeviceAuthorizationEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义设备授权端点
 */
@Component
public class DeviceAuthorizationEndpointCustomizer implements Customizer<OAuth2DeviceAuthorizationEndpointConfigurer> {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制设备授权端点
     *
     * @param configurer OAuth2DeviceAuthorizationEndpointConfigurer 对象
     */
    @Override
    public void customize(OAuth2DeviceAuthorizationEndpointConfigurer configurer) {
        // 设置设备授权失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
