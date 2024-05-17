package com.gls.gemini.uaa.boot.web.service;

import cn.hutool.core.util.NumberUtil;
import com.gls.gemini.uaa.boot.web.converter.AuthorizationConsentConverter;
import com.gls.gemini.upms.sdk.feign.AuthorizationConsentInfoFeign;
import com.gls.gemini.upms.sdk.vo.AuthorizationConsentInfoVo;
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
    @Resource
    private AuthorizationConsentConverter authorizationConsentConverter;
    @Resource
    private AuthorizationConsentInfoFeign authorizationConsentInfoFeign;

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        authorizationConsentInfoFeign.insert(authorizationConsentConverter.convert(authorizationConsent));
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        Long clientId = NumberUtil.parseLong(authorizationConsent.getRegisteredClientId());
        String principalName = authorizationConsent.getPrincipalName();
        Long userId = authorizationConsentConverter.getUserId(principalName);
        AuthorizationConsentInfoVo vo = authorizationConsentInfoFeign.findByClientIdAndUserId(clientId, userId).getData();
        if (vo != null) {
            authorizationConsentInfoFeign.delete(vo.getId());
        }
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Long clientId = NumberUtil.parseLong(registeredClientId);
        Long userId = authorizationConsentConverter.getUserId(principalName);
        return authorizationConsentConverter.reverse(authorizationConsentInfoFeign.findByClientIdAndUserId(clientId, userId).getData());
    }
}
