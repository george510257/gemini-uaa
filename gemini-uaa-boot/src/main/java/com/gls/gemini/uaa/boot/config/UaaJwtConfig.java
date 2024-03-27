package com.gls.gemini.uaa.boot.config;

import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Jwt配置
 */
@Configuration
public class UaaJwtConfig {

    /**
     * 生成RSA密钥对
     *
     * @return 密钥对
     */
    @Bean
    @ConditionalOnMissingBean
    public KeyPair keyPair() {
        return KeyUtil.generateKeyPair(AsymmetricAlgorithm.RSA.getValue(), 2048);
    }

    /**
     * JWK源
     *
     * @param keyPair 密钥对
     * @return JWK源
     */
    @Bean
    @ConditionalOnMissingBean
    public JWKSource<SecurityContext> jwkSource(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.fastUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * Jwt解码器
     *
     * @param jwkSource JWK源
     * @return Jwt解码器
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    /**
     * Jwt编码器
     *
     * @param jwkSource JWK源
     * @return Jwt编码器
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    /**
     * Jwt编码上下文OAuth2令牌定制器
     *
     * @return Jwt编码上下文OAuth2令牌定制器
     */
    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtEncodingContextOAuth2TokenCustomizer() {
        return context -> {
            // 获取用户信息
            Object user = context.getPrincipal().getPrincipal();
            if (user instanceof UserDetails userDetails) {
                // 设置用户信息
                context.getClaims().claim("user_info", userDetails);
            }
        };
    }
}
