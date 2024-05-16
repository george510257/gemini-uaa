package com.gls.gemini.uaa.boot.web.converter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.uaa.boot.web.service.ClientService;
import com.gls.gemini.uaa.boot.web.service.UserService;
import com.gls.gemini.upms.sdk.vo.AuthorizationInfoVo;
import jakarta.annotation.Resource;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationCode;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 授权信息转换器
 */
@Component
public class AuthorizationConverter implements BaseConverter<OAuth2Authorization, AuthorizationInfoVo> {

    @Resource
    private UserService userService;
    @Resource
    private ClientService clientService;

    @Override
    public AuthorizationInfoVo convert(OAuth2Authorization authorization) {
        return convertCopy(authorization, new AuthorizationInfoVo());
    }

    @Override
    public AuthorizationInfoVo convertCopy(OAuth2Authorization authorization, AuthorizationInfoVo vo) {
        vo.setClientId(NumberUtil.parseLong(authorization.getRegisteredClientId()));
        vo.setUserId(getUserIdByPrincipalName(authorization.getPrincipalName()));
        vo.setAuthorizationGrantType(authorization.getAuthorizationGrantType().getValue());
        vo.setAuthorizedScopes(authorization.getAuthorizedScopes());
        vo.setAttributes(authorization.getAttributes());

        vo.setState(authorization.getAttribute(OAuth2ParameterNames.STATE));
        // authorizationCode
        OAuth2Authorization.Token<OAuth2AuthorizationCode> authorizationCode = authorization.getToken(OAuth2AuthorizationCode.class);
        if (authorizationCode != null) {
            OAuth2AuthorizationCode token = authorizationCode.getToken();
            vo.setAuthorizationCodeValue(token.getTokenValue());
            if (token.getIssuedAt() != null) {
                vo.setAuthorizationCodeIssuedAt(Date.from(token.getIssuedAt()));
            }
            if (token.getExpiresAt() != null) {
                vo.setAuthorizationCodeExpiresAt(Date.from(token.getExpiresAt()));
            }
            vo.setAuthorizationCodeMetadata(authorizationCode.getMetadata());
        }
        // accessToken
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getToken(OAuth2AccessToken.class);
        if (accessToken != null) {
            OAuth2AccessToken token = accessToken.getToken();
            vo.setAccessTokenValue(token.getTokenValue());
            if (token.getIssuedAt() != null) {
                vo.setAccessTokenIssuedAt(Date.from(token.getIssuedAt()));
            }
            if (token.getExpiresAt() != null) {
                vo.setAccessTokenExpiresAt(Date.from(token.getExpiresAt()));
            }
            vo.setAccessTokenType(token.getTokenType().getValue());
            vo.setAccessTokenScopes(String.join(",", token.getScopes()));
            vo.setAccessTokenMetadata(accessToken.getMetadata());
        }
        // refreshToken
        OAuth2Authorization.Token<OAuth2AccessToken> refreshToken = authorization.getToken(OAuth2AccessToken.class);
        if (refreshToken != null) {
            OAuth2AccessToken token = refreshToken.getToken();
            vo.setRefreshTokenValue(token.getTokenValue());
            if (token.getIssuedAt() != null) {
                vo.setRefreshTokenIssuedAt(Date.from(token.getIssuedAt()));
            }
            if (token.getExpiresAt() != null) {
                vo.setRefreshTokenExpiresAt(Date.from(token.getExpiresAt()));
            }
            vo.setRefreshTokenMetadata(refreshToken.getMetadata());
        }
        // oidc id token
        OAuth2Authorization.Token<OidcIdToken> oidcIdToken = authorization.getToken(OidcIdToken.class);
        if (oidcIdToken != null) {
            OidcIdToken token = oidcIdToken.getToken();
            vo.setOidcIdTokenValue(token.getTokenValue());
            if (token.getIssuedAt() != null) {
                vo.setOidcIdTokenIssuedAt(Date.from(token.getIssuedAt()));
            }
            if (token.getExpiresAt() != null) {
                vo.setOidcIdTokenExpiresAt(Date.from(token.getExpiresAt()));
            }
            vo.setOidcIdTokenMetadata(oidcIdToken.getMetadata());
        }
        // user code token
        OAuth2Authorization.Token<OAuth2UserCode> userCode = authorization.getToken(OAuth2UserCode.class);
        if (userCode != null) {
            OAuth2UserCode token = userCode.getToken();
            vo.setUserCodeValue(token.getTokenValue());
            if (token.getIssuedAt() != null) {
                vo.setUserCodeIssuedAt(Date.from(token.getIssuedAt()));
            }
            if (token.getExpiresAt() != null) {
                vo.setUserCodeExpiresAt(Date.from(token.getExpiresAt()));
            }
            vo.setUserCodeMetadata(userCode.getMetadata());
        }
        // device code token
        OAuth2Authorization.Token<OAuth2DeviceCode> deviceCode = authorization.getToken(OAuth2DeviceCode.class);
        if (deviceCode != null) {
            OAuth2DeviceCode token = deviceCode.getToken();
            vo.setDeviceCodeValue(token.getTokenValue());
            if (token.getIssuedAt() != null) {
                vo.setDeviceCodeIssuedAt(Date.from(token.getIssuedAt()));
            }
            if (token.getExpiresAt() != null) {
                vo.setDeviceCodeExpiresAt(Date.from(token.getExpiresAt()));
            }
            vo.setDeviceCodeMetadata(deviceCode.getMetadata());
        }
        return vo;
    }

    @Override
    public OAuth2Authorization reverse(AuthorizationInfoVo vo) {
        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(getRegisteredClientByClientId(vo.getClientId()));
        return reverseByBuilder(vo, builder);
    }

    @Override
    public OAuth2Authorization reverseCopy(AuthorizationInfoVo vo, OAuth2Authorization oAuth2Authorization) {
        OAuth2Authorization.Builder builder = OAuth2Authorization.from(oAuth2Authorization);
        return reverseByBuilder(vo, builder);
    }

    private OAuth2Authorization reverseByBuilder(AuthorizationInfoVo vo, OAuth2Authorization.Builder builder) {
        builder.id(vo.getId().toString())
                .principalName(getPrincipalNameByUserId(vo.getUserId()))
                .authorizationGrantType(new AuthorizationGrantType(vo.getAuthorizationGrantType()))
                .attributes(attrs -> attrs.putAll(vo.getAttributes()));
        if (vo.getState() != null) {
            builder.attribute(OAuth2ParameterNames.STATE, vo.getState());
        }
        // authorizationCode
        if (vo.getAuthorizationCodeValue() != null) {
            OAuth2AuthorizationCode authorizationCode = new OAuth2AuthorizationCode(
                    vo.getAuthorizationCodeValue(),
                    vo.getAuthorizationCodeIssuedAt().toInstant(),
                    vo.getAuthorizationCodeExpiresAt().toInstant());
            builder.token(authorizationCode, metadata -> metadata.putAll(vo.getAuthorizationCodeMetadata()));
        }
        // accessToken
        if (vo.getAccessTokenValue() != null) {
            OAuth2AccessToken accessToken = new OAuth2AccessToken(
                    OAuth2AccessToken.TokenType.BEARER,
                    vo.getAccessTokenValue(),
                    vo.getAccessTokenIssuedAt().toInstant(),
                    vo.getAccessTokenExpiresAt().toInstant(),
                    CollUtil.newHashSet(vo.getAccessTokenScopes().split(",")));
            builder.token(accessToken, metadata -> metadata.putAll(vo.getAccessTokenMetadata()));
        }
        // refreshToken
        if (vo.getRefreshTokenValue() != null) {
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(
                    vo.getRefreshTokenValue(),
                    vo.getRefreshTokenIssuedAt().toInstant(),
                    vo.getRefreshTokenExpiresAt().toInstant());
            builder.token(refreshToken, metadata -> metadata.putAll(vo.getRefreshTokenMetadata()));
        }
        // oidc id token
        if (vo.getOidcIdTokenValue() != null) {
            OidcIdToken oidcIdToken = new OidcIdToken(
                    vo.getOidcIdTokenValue(),
                    vo.getOidcIdTokenIssuedAt().toInstant(),
                    vo.getOidcIdTokenExpiresAt().toInstant(),
                    (Map<String, Object>) vo.getOidcIdTokenMetadata().get(OAuth2Authorization.Token.CLAIMS_METADATA_NAME));
            builder.token(oidcIdToken, metadata -> metadata.putAll(vo.getOidcIdTokenMetadata()));
        }
        // user code token
        if (vo.getUserCodeValue() != null) {
            OAuth2UserCode userCode = new OAuth2UserCode(
                    vo.getUserCodeValue(),
                    vo.getUserCodeIssuedAt().toInstant(),
                    vo.getUserCodeExpiresAt().toInstant());
            builder.token(userCode, metadata -> metadata.putAll(vo.getUserCodeMetadata()));
        }
        // device code token
        if (vo.getDeviceCodeValue() != null) {
            OAuth2DeviceCode deviceCode = new OAuth2DeviceCode(
                    vo.getDeviceCodeValue(),
                    vo.getDeviceCodeIssuedAt().toInstant(),
                    vo.getDeviceCodeExpiresAt().toInstant());
            builder.token(deviceCode, metadata -> metadata.putAll(vo.getDeviceCodeMetadata()));
        }
        return builder.build();
    }

    private Long getUserIdByPrincipalName(String principalName) {
        return userService.loadUserByUsername(principalName).getId();
    }

    private String getPrincipalNameByUserId(Long userId) {
        return userService.findById(userId).getUsername();
    }

    private RegisteredClient getRegisteredClientByClientId(Long clientId) {
        return clientService.findById(String.valueOf(clientId));
    }
}
