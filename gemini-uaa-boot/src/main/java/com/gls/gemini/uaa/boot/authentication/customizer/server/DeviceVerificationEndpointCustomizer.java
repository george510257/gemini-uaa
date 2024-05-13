package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2DeviceVerificationEndpointConfigurer;

/**
 * 自定义设备验证端点
 */
public class DeviceVerificationEndpointCustomizer implements Customizer<OAuth2DeviceVerificationEndpointConfigurer> {
    @Override
    public void customize(OAuth2DeviceVerificationEndpointConfigurer configurer) {

    }
}
