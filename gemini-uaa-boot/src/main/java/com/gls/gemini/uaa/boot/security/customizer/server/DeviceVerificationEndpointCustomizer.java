package com.gls.gemini.uaa.boot.security.customizer.server;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2DeviceVerificationEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义设备验证端点
 */
@Component
public class DeviceVerificationEndpointCustomizer implements Customizer<OAuth2DeviceVerificationEndpointConfigurer> {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void customize(OAuth2DeviceVerificationEndpointConfigurer configurer) {
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
