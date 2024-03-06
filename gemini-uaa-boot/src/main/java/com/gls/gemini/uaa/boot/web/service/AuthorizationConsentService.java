package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.data.redis.helper.RedisHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Service;

/**
 * 授权同意服务
 */
@Slf4j
@Service
public class AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    /**
     * 前缀
     */
    private static final String PREFIX = "token:consent:";

    @Resource
    private RedisHelper redisHelper;

    /**
     * 保存授权同意
     *
     * @param authorizationConsent the {@link OAuth2AuthorizationConsent}
     */
    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        log.info("save authorizationConsent:{}", authorizationConsent);
        redisHelper.set(buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName()), authorizationConsent);
    }

    /**
     * 删除授权同意
     *
     * @param authorizationConsent the {@link OAuth2AuthorizationConsent}
     */
    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        log.info("remove authorizationConsent:{}", authorizationConsent);
        redisHelper.del(buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName()));
    }

    /**
     * 根据注册客户端和主体查找授权同意
     *
     * @param registeredClientId the id of the RegisteredClient
     * @param principalName      the name of the Principal
     * @return
     */
    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        log.info("findById registeredClientId:{}, principalName:{}", registeredClientId, principalName);
        return redisHelper.get(buildKey(registeredClientId, principalName), OAuth2AuthorizationConsent.class);
    }

    /**
     * 构建key
     *
     * @param registeredClientId the id of the RegisteredClient
     * @param principalName      the name of the Principal
     * @return key
     */
    private String buildKey(String registeredClientId, String principalName) {
        return PREFIX + registeredClientId + ":" + principalName;
    }
}
