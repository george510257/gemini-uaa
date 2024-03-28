package com.gls.gemini.uaa.boot.authentication.password;

import com.gls.gemini.uaa.boot.properties.UaaConstants;
import com.gls.gemini.uaa.boot.util.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * OAuth2密码认证转换器
 */
public class OAuth2PasswordAuthenticationConverter implements AuthenticationConverter {
    /**
     * 将请求中的参数转换为OAuth2PasswordAuthenticationToken
     *
     * @param request 请求
     * @return OAuth2PasswordAuthenticationToken 对象
     */
    @Override
    public Authentication convert(HttpServletRequest request) {
        // 获取请求中的参数
        MultiValueMap<String, String> parameters = AuthUtil.getFormParameters(request);

        // 获取授权类型
        String grantType = parameters.getFirst(OAuth2ParameterNames.GRANT_TYPE);
        if (!AuthorizationGrantType.PASSWORD.getValue().equals(grantType)) {
            return null;
        }
        // 获取客户端主体
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        // 获取请求的范围
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope) &&
                parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            AuthUtil.throwError(
                    OAuth2ErrorCodes.INVALID_REQUEST,
                    OAuth2ParameterNames.SCOPE,
                    UaaConstants.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }
        // 请求的范围
        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(
                    Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }
        // 用户名 (REQUIRED)
        String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
        if (!StringUtils.hasText(username) || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
            AuthUtil.throwError(
                    OAuth2ErrorCodes.INVALID_REQUEST,
                    OAuth2ParameterNames.USERNAME,
                    UaaConstants.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        // 密码 (REQUIRED)
        String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
        if (!StringUtils.hasText(password) || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
            AuthUtil.throwError(
                    OAuth2ErrorCodes.INVALID_REQUEST,
                    OAuth2ParameterNames.PASSWORD,
                    UaaConstants.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        // 额外参数
        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) &&
                    !key.equals(OAuth2ParameterNames.SCOPE)) {
                additionalParameters.put(key, (value.size() == 1) ? value.get(0) : value.toArray(new String[0]));
            }
        });
        // 返回OAuth2PasswordAuthenticationToken对象
        return new OAuth2PasswordAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
    }
}
