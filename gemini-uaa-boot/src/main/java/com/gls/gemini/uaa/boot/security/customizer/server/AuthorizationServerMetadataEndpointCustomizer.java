package com.gls.gemini.uaa.boot.security.customizer.server;

import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationServerMetadata;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerMetadataEndpointConfigurer;
import org.springframework.stereotype.Component;

/**
 * 自定义授权源端点
 */
@Component
public class AuthorizationServerMetadataEndpointCustomizer implements Customizer<OAuth2AuthorizationServerMetadataEndpointConfigurer> {
    /**
     * 定制授权源端点
     *
     * @param configurer 授权源端点配置器
     */
    @Override
    public void customize(OAuth2AuthorizationServerMetadataEndpointConfigurer configurer) {
        // 定制授权源端点
        configurer.authorizationServerMetadataCustomizer(this::customizeAuthorizationServerMetadata);
    }

    /**
     * 定制授权源元数据
     *
     * @param builder 授权源元数据构建器
     */
    private void customizeAuthorizationServerMetadata(OAuth2AuthorizationServerMetadata.Builder builder) {
    }
}
