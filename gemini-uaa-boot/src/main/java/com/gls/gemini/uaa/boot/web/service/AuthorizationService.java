package com.gls.gemini.uaa.boot.web.service;

import cn.hutool.core.util.NumberUtil;
import com.gls.gemini.uaa.boot.web.converter.AuthorizationConverter;
import com.gls.gemini.upms.sdk.feign.AuthorizationInfoFeign;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2DeviceCode;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2UserCode;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 授权服务
 */
@Slf4j
@Service
public class AuthorizationService implements OAuth2AuthorizationService {

    @Resource
    private AuthorizationConverter authorizationConverter;
    @Resource
    private AuthorizationInfoFeign authorizationInfoFeign;

    /**
     * 保存授权
     *
     * @param authorization the {@link OAuth2Authorization}
     */
    @Override
    public void save(OAuth2Authorization authorization) {
        authorizationInfoFeign.save(authorizationConverter.convert(authorization));
    }

    /**
     * 删除授权
     *
     * @param authorization the {@link OAuth2Authorization}
     */
    @Override
    public void remove(OAuth2Authorization authorization) {
        authorizationInfoFeign.delete(NumberUtil.parseLong(authorization.getId()));
    }

    /**
     * 根据id查找授权
     *
     * @param id the authorization identifier
     * @return the {@link OAuth2Authorization} or {@code null} if not found
     */
    @Override
    public OAuth2Authorization findById(String id) {
        return authorizationConverter.reverse(authorizationInfoFeign.get(NumberUtil.parseLong(id)).getData());
    }

    /**
     * 根据token查找授权
     *
     * @param token     the token credential
     * @param tokenType the {@link OAuth2TokenType token type}
     * @return the {@link OAuth2Authorization} or {@code null} if not found
     */
    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        List<OAuth2Authorization> authorizations = authorizationConverter.reverseList(authorizationInfoFeign.getByToken(token).getData());
        if (authorizations == null || authorizations.isEmpty()) {
            return null;
        }
        return authorizations.stream()
                .filter(authorization -> hasToken(authorization, token, tokenType))
                .findFirst()
                .orElse(null);
    }

    /**
     * 是否有token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @param tokenType     the {@link OAuth2TokenType token type}
     * @return true if the {@link OAuth2Authorization} has the token, otherwise false
     */
    private boolean hasToken(OAuth2Authorization authorization, String token, OAuth2TokenType tokenType) {
        log.info("hasToken authorization:{}, token:{}, tokenType:{}", authorization, token, tokenType);
        if (tokenType == null) {
            return matchesState(authorization, token) ||
                    matchesAuthorizationCode(authorization, token) ||
                    matchesAccessToken(authorization, token) ||
                    matchesIdToken(authorization, token) ||
                    matchesRefreshToken(authorization, token) ||
                    matchesDeviceCode(authorization, token) ||
                    matchesUserCode(authorization, token);
        } else if (OAuth2ParameterNames.STATE.equals(tokenType.getValue())) {
            return matchesState(authorization, token);
        } else if (OAuth2ParameterNames.CODE.equals(tokenType.getValue())) {
            return matchesAuthorizationCode(authorization, token);
        } else if (OAuth2TokenType.ACCESS_TOKEN.equals(tokenType)) {
            return matchesAccessToken(authorization, token);
        } else if (OidcParameterNames.ID_TOKEN.equals(tokenType.getValue())) {
            return matchesIdToken(authorization, token);
        } else if (OAuth2TokenType.REFRESH_TOKEN.equals(tokenType)) {
            return matchesRefreshToken(authorization, token);
        } else if (OAuth2ParameterNames.DEVICE_CODE.equals(tokenType.getValue())) {
            return matchesDeviceCode(authorization, token);
        } else if (OAuth2ParameterNames.USER_CODE.equals(tokenType.getValue())) {
            return matchesUserCode(authorization, token);
        }
        return false;
    }

    /**
     * 匹配state token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the state token, otherwise false
     */
    private boolean matchesState(OAuth2Authorization authorization, String token) {
        return token.equals(authorization.getAttribute(OAuth2ParameterNames.STATE));
    }

    /**
     * 匹配authorization code token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the authorization code token, otherwise false
     */
    private boolean matchesAuthorizationCode(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
        if (authorizationCode == null) {
            return false;
        }
        return token.equals(authorizationCode.getToken().getTokenValue());
    }

    /**
     * 匹配access token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the access token, otherwise false
     */
    private boolean matchesAccessToken(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        if (accessToken == null) {
            return false;
        }
        return token.equals(accessToken.getToken().getTokenValue());
    }

    /**
     * 匹配id token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the id token, otherwise false
     */
    private boolean matchesIdToken(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OidcIdToken> idTokenToken = authorization.getToken(OidcIdToken.class);
        if (idTokenToken == null) {
            return false;
        }
        return token.equals(idTokenToken.getToken().getTokenValue());
    }

    /**
     * 匹配refresh token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the refresh token, otherwise false
     */
    private boolean matchesRefreshToken(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();
        if (refreshToken == null) {
            return false;
        }
        return token.equals(refreshToken.getToken().getTokenValue());
    }

    /**
     * 匹配device code token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the device code token, otherwise false
     */
    private boolean matchesDeviceCode(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCodeToken = authorization.getToken(OAuth2DeviceCode.class);
        if (deviceCodeToken == null) {
            return false;
        }
        return token.equals(deviceCodeToken.getToken().getTokenValue());
    }

    /**
     * 匹配user code token
     *
     * @param authorization the {@link OAuth2Authorization}
     * @param token         the token credential
     * @return true if the {@link OAuth2Authorization} has the user code token, otherwise false
     */
    private boolean matchesUserCode(OAuth2Authorization authorization, String token) {
        OAuth2Authorization.Token<OAuth2UserCode> userCodeToken = authorization.getToken(OAuth2UserCode.class);
        if (userCodeToken == null) {
            return false;
        }
        return token.equals(userCodeToken.getToken().getTokenValue());
    }

}
