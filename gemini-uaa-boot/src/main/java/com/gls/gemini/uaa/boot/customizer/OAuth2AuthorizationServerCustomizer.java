package com.gls.gemini.uaa.boot.customizer;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义OAuth2授权服务器
 */
@Component
public class OAuth2AuthorizationServerCustomizer implements Customizer<OAuth2AuthorizationServerConfigurer> {
    @Override
    public void customize(OAuth2AuthorizationServerConfigurer configurer) {
        // 配置OIDC
        configurer.oidc(this::oidcCustomize);
    }

    private void oidcCustomize(OidcConfigurer configurer) {

    }
}
