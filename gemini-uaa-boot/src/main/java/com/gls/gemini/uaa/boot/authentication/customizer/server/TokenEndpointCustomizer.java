package com.gls.gemini.uaa.boot.authentication.customizer.server;

import com.gls.gemini.uaa.boot.authentication.password.OAuth2PasswordAuthenticationConverter;
import com.gls.gemini.uaa.boot.authentication.password.OAuth2PasswordAuthenticationProvider;
import com.gls.gemini.uaa.boot.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2TokenEndpointConfigurer;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

/**
 * 自定义令牌端点
 */
@RequiredArgsConstructor
public class TokenEndpointCustomizer implements Customizer<OAuth2TokenEndpointConfigurer> {

    private final HttpSecurity httpSecurity;

    /**
     * 定制令牌端点
     *
     * @param configurer OAuth2TokenEndpointConfigurer 对象
     */
    @Override
    public void customize(OAuth2TokenEndpointConfigurer configurer) {
        // 获取认证管理器
        AuthenticationManager authenticationManager = AuthUtil.getAuthenticationManager();
        // 获取授权服务
        OAuth2AuthorizationService authorizationService = AuthUtil.getAuthorizationService(httpSecurity);
        // 获取令牌生成器
        OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator = AuthUtil.getTokenGenerator(httpSecurity);

        Assert.notNull(authenticationManager, "AuthenticationManager is required");
        Assert.notNull(authorizationService, "OAuth2AuthorizationService is required");
        Assert.notNull(tokenGenerator, "OAuth2TokenGenerator is required");

        // 配置密码模式
        configurer.authenticationProvider(new OAuth2PasswordAuthenticationProvider(authenticationManager, authorizationService, tokenGenerator));
        configurer.accessTokenRequestConverter(new OAuth2PasswordAuthenticationConverter());
    }

}
