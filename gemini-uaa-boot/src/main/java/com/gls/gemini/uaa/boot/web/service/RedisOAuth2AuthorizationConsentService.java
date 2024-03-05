package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.data.redis.helper.RedisHelper;
import jakarta.annotation.Resource;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Redis OAuth2 授权同意服务
 */
@Service
public class RedisOAuth2AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    /**
     * Redis 帮助类
     */
    @Resource
    private RedisHelper redisHelper;

    /**
     * 保存授权同意
     *
     * @param authorizationConsent 授权同意
     */
    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        String key = this.buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
        redisHelper.set(key, authorizationConsent, 10L, TimeUnit.MINUTES);
    }

    /**
     * 删除授权同意
     *
     * @param authorizationConsent 授权同意
     */
    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        String key = this.buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName());
        redisHelper.delete(key);
    }

    /**
     * 根据注册客户端ID和主体名称查找授权同意
     *
     * @param registeredClientId 注册客户端ID
     * @param principalName      主体名称
     * @return 授权同意
     */
    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        String key = this.buildKey(registeredClientId, principalName);
        return (OAuth2AuthorizationConsent) redisHelper.get(key);
    }

    /**
     * 构建Key
     *
     * @param registeredClientId 注册客户端ID
     * @param principalName      主体名称
     * @return Key
     */
    private String buildKey(String registeredClientId, String principalName) {
        return "authorization:consent:" + registeredClientId + ":" + principalName;
    }
}
