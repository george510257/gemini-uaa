package com.gls.gemini.uaa.boot.authentication.customizer.server;

import jakarta.annotation.Resource;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2ClientAuthenticationConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义OAuth2客户端认证
 */
@Component
public class ClientAuthenticationCustomizer implements Customizer<OAuth2ClientAuthenticationConfigurer> {

    //    @Resource
//    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制OAuth2客户端认证
     *
     * @param configurer OAuth2ClientAuthenticationConfigurer 对象
     */
    @Override
    public void customize(OAuth2ClientAuthenticationConfigurer configurer) {
        // 设置认证成功处理器
//        configurer.authenticationSuccessHandler(authenticationSuccessHandler);
        // 设置认证失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }
}
