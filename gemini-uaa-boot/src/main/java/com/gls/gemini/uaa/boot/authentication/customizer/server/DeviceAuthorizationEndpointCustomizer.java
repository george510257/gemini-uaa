package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2DeviceAuthorizationEndpointConfigurer;

/**
 * 自定义设备授权端点
 */
public class DeviceAuthorizationEndpointCustomizer implements Customizer<OAuth2DeviceAuthorizationEndpointConfigurer> {
    /**
     * 定制设备授权端点
     *
     * @param configurer OAuth2DeviceAuthorizationEndpointConfigurer 对象
     */
    @Override
    public void customize(OAuth2DeviceAuthorizationEndpointConfigurer configurer) {
    }
}
