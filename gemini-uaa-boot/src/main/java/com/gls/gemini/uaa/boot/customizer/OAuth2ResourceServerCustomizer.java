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

    /**
     * 用户详情服务
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 自定义资源服务器
     *
     * @param configurer 资源服务器配置器
     */
    @Override
    public void customize(OAuth2ResourceServerConfigurer<HttpSecurity> configurer) {
        // 配置资源服务器 JWT 验证
        configurer.jwt(this::jwtCustomize);
    }

    /**
     * JWT 验证定制
     *
     * @param configurer JWT 验证配置器
     */
    private void jwtCustomize(OAuth2ResourceServerConfigurer<HttpSecurity>.JwtConfigurer configurer) {
        // 配置资源服务器 JWT 验证转换器
        configurer.jwtAuthenticationConverter(this::jwtAuthenticationConverter);
    }

    /**
     * JWT 认证转换器
     *
     * @param jwt JWT
     * @return 认证令牌
     */
    private AbstractAuthenticationToken jwtAuthenticationConverter(Jwt jwt) {
        // 获取用户名
        String username = jwt.getSubject();
        // 创建用户名密码认证令牌
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.getAuthorities());
    }
}
