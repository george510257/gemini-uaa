package com.gls.gemini.uaa.boot.security.customizer.server.oidc;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OidcClientRegistrationEndpointConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 客户端注册端点定制器
 */
@Component
public class ClientRegistrationEndpointCustomizer implements Customizer<OidcClientRegistrationEndpointConfigurer> {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制客户端注册端点
     *
     * @param configurer 客户端注册端点配置器
     */
    @Override
    public void customize(OidcClientRegistrationEndpointConfigurer configurer) {
        // 设置客户端注册失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
