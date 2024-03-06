package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.data.redis.helper.RedisHelper;
import jakarta.annotation.Resource;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationConsentService implements OAuth2AuthorizationConsentService {
    private static final String PREFIX = "token:consent:";

    @Resource
    private RedisHelper redisHelper;

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        redisHelper.set(buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName()), authorizationConsent);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        redisHelper.del(buildKey(authorizationConsent.getRegisteredClientId(), authorizationConsent.getPrincipalName()));
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        return redisHelper.get(buildKey(registeredClientId, principalName), OAuth2AuthorizationConsent.class);
    }

    private String buildKey(String registeredClientId, String principalName) {
        return PREFIX + registeredClientId + ":" + principalName;
    }
}
