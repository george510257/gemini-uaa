package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.stereotype.Component;

/**
 * 授权服务器定制
 */
@Component
public class OAuth2AuthorizationServerCustomizer implements Customizer<OAuth2AuthorizationServerConfigurer> {
    /**
     * 自定义授权服务器
     *
     * @param configurer 授权服务器配置器
     */
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // OIDC 配置 - 默认
        configurer.oidc(Customizer.withDefaults());
    }

}
