package com.gls.gemini.uaa.boot.authentication.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2ClientAuthenticationConfigurer;

/**
 * 自定义OAuth2客户端认证
 */
public class ClientAuthenticationCustomizer implements Customizer<OAuth2ClientAuthenticationConfigurer> {
    /**
     * 定制OAuth2客户端认证
     *
     * @param configurer OAuth2ClientAuthenticationConfigurer 对象
     */
    @Override
    public void customize(OAuth2ClientAuthenticationConfigurer configurer) {
    }
}
