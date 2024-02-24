package com.gls.gemini.uaa.boot.customizer;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * 资源服务器定制
 */
@Component
public class OAuth2ResourceServerCustomizer implements Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        // 配置资源服务器 JWT 验证
        configurer.jwt(this::jwtCustomize);
    }

    private void jwtCustomize(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer configurer) {
        // 配置资源服务器 JWT 验证转换器
        configurer.jwtAuthenticationConverter(this::jwtAuthenticationConverter);

    }

    private AbstractAuthenticationToken jwtAuthenticationConverter(Jwt jwt) {
        // 获取用户名
        String username = jwt.getSubject();
        // 创建用户名密码认证令牌
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
    }
}
