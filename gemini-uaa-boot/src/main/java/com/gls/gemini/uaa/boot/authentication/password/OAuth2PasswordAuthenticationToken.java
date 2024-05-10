package com.gls.gemini.uaa.boot.authentication.password;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;
import java.util.Set;

/**
 * 密码授权token信息
 */
@Getter
public class OAuth2PasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
    /**
     * 授权范围
     * -- GETTER --
     * 获取凭证
     *
     * @return 凭证
     */
    private final Set<String> scopes;

    /**
     * 构造函数
     *
     * @param clientPrincipal      客户端主体
     * @param scopes               授权范围
     * @param additionalParameters 额外参数
     */
    public OAuth2PasswordAuthenticationToken(Authentication clientPrincipal, Set<String> scopes, Map<String, Object> additionalParameters) {
        super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
        this.scopes = scopes;
    }

}
