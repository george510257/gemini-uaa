package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import com.gls.gemini.upms.sdk.vo.AuthorizationConsentInfoVo;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthorizationConsentConverter implements BaseConverter<OAuth2AuthorizationConsent, AuthorizationConsentInfoVo> {
    @Resource
    private UserInfoFeign userInfoFeign;

    @Override
    public AuthorizationConsentInfoVo convert(OAuth2AuthorizationConsent authorizationConsent) {
        AuthorizationConsentInfoVo authorizationConsentInfoVo = new AuthorizationConsentInfoVo();
        convertCopy(authorizationConsent, authorizationConsentInfoVo);
        return authorizationConsentInfoVo;
    }

    @Override
    public void convertCopy(OAuth2AuthorizationConsent authorizationConsent, AuthorizationConsentInfoVo authorizationConsentInfoVo) {
        if (authorizationConsent == null) {
            return;
        }
        authorizationConsentInfoVo.setClientId(Long.valueOf(authorizationConsent.getRegisteredClientId()));
        authorizationConsentInfoVo.setUserId(userInfoFeign.getUserInfoByUsername(authorizationConsent.getPrincipalName()).getData().getId());
        authorizationConsentInfoVo.setAuthorities(authorizationConsent.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
    }

    @Override
    public OAuth2AuthorizationConsent reverse(AuthorizationConsentInfoVo authorizationConsentInfoVo) {
        String clientId = String.valueOf(authorizationConsentInfoVo.getClientId());
        String principalName = userInfoFeign.get(authorizationConsentInfoVo.getUserId()).getData().getUsername();
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(clientId, principalName);
        authorizationConsentInfoVo.getAuthorities().forEach(authority -> builder.authority(new SimpleGrantedAuthority(authority)));
        return builder.build();
    }

    @Override
    public void reverseCopy(AuthorizationConsentInfoVo authorizationConsentInfoVo, OAuth2AuthorizationConsent authorizationConsent) {
        throw new UnsupportedOperationException();
    }
}
