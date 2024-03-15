package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import com.gls.gemini.upms.sdk.vo.AuthorizationInfoVo;
import jakarta.annotation.Resource;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConverter implements BaseConverter<OAuth2Authorization, AuthorizationInfoVo> {

    @Resource
    private UserInfoFeign userInfoFeign;

    @Override
    public AuthorizationInfoVo convert(OAuth2Authorization oauth2Authorization) {
        AuthorizationInfoVo authorizationInfoVo = new AuthorizationInfoVo();
        convertCopy(oauth2Authorization, authorizationInfoVo);
        return authorizationInfoVo;
    }

    @Override
    public void convertCopy(OAuth2Authorization oauth2Authorization, AuthorizationInfoVo authorizationInfoVo) {
        if (oauth2Authorization == null) {
            return;
        }
        authorizationInfoVo.setClientId(Long.valueOf(oauth2Authorization.getRegisteredClientId()));
        authorizationInfoVo.setUserId(userInfoFeign.getUserInfoByUsername(oauth2Authorization.getPrincipalName()).getData().getId());
        authorizationInfoVo.setAuthorizationGrantType(oauth2Authorization.getAuthorizationGrantType().getValue());
        authorizationInfoVo.setAuthorizedScopes(oauth2Authorization.getAuthorizedScopes());
        authorizationInfoVo.setAttributes(oauth2Authorization.getAttributes());
        authorizationInfoVo.setState(getState(oauth2Authorization));
        authorizationInfoVo.setAuthorizationCodeValue(getAuthorizationCodeValue(oauth2Authorization));
    }



    @Override
    public OAuth2Authorization reverse(AuthorizationInfoVo authorizationInfoVo) {
        return null;
    }

    @Override
    public void reverseCopy(AuthorizationInfoVo authorizationInfoVo, OAuth2Authorization oauth2Authorization) {

    }

    private String getState(OAuth2Authorization oauth2Authorization) {
        return oauth2Authorization.getAttribute("state");
    }

    private String getAuthorizationCodeValue(OAuth2Authorization oauth2Authorization) {
    }
}
