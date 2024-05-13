package com.gls.gemini.uaa.boot.authentication.customizer.server;

import com.gls.gemini.uaa.boot.authentication.password.OAuth2PasswordAuthenticationConverter;
import com.gls.gemini.uaa.boot.authentication.password.OAuth2PasswordAuthenticationProvider;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 自定义令牌端点
 */
@Component
public class TokenEndpointCustomizer implements Customizer<OAuth2TokenEndpointConfigurer> {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private OAuth2AuthorizationService authorizationService;
    @Resource
    private OAuth2TokenGenerator<? extends OAuth2Token> oauth2TokenGenerator;
    //    @Resource
//    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 定制令牌端点
     *
     * @param configurer OAuth2TokenEndpointConfigurer 对象
     */
    @Override
    public void customize(OAuth2TokenEndpointConfigurer configurer) {

        // 配置密码模式
        configurer.authenticationProvider(new OAuth2PasswordAuthenticationProvider(authenticationManager, authorizationService, oauth2TokenGenerator));
        configurer.accessTokenRequestConverter(new OAuth2PasswordAuthenticationConverter());

        // 设置认证成功处理器
//        configurer.accessTokenResponseHandler(authenticationSuccessHandler);
        // 设置认证失败处理器
        configurer.errorResponseHandler(authenticationFailureHandler);
    }

}
