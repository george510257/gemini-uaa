package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.upms.sdk.vo.ClientInfoVo;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * 客户端转换器
 */
@Component
public class ClientConverter implements BaseConverter<RegisteredClient, ClientInfoVo> {

    @Override
    public ClientInfoVo convert(RegisteredClient registeredClient) {
        ClientInfoVo clientInfoVo = new ClientInfoVo();
        this.convertCopy(registeredClient, clientInfoVo);
        return clientInfoVo;
    }

    @Override
    public void convertCopy(RegisteredClient registeredClient, ClientInfoVo clientInfoVo) {
        if (registeredClient == null || clientInfoVo == null) {
            return;
        }
        if (registeredClient.getClientId() != null) {
            clientInfoVo.setClientId(registeredClient.getClientId());
        }
        if (registeredClient.getClientIdIssuedAt() != null) {
            clientInfoVo.setClientIdIssuedAt(Date.from(registeredClient.getClientIdIssuedAt()));
        }
        if (registeredClient.getClientSecret() != null) {
            clientInfoVo.setClientSecret(registeredClient.getClientSecret());
        }
        if (registeredClient.getClientSecretExpiresAt() != null) {
            clientInfoVo.setClientSecretExpiresAt(Date.from(registeredClient.getClientSecretExpiresAt()));
        }
        if (registeredClient.getClientName() != null) {
            clientInfoVo.setClientName(registeredClient.getClientName());
        }
        if (registeredClient.getClientAuthenticationMethods() != null) {
            clientInfoVo.setClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods()
                    .stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toSet()));
        }
        if (registeredClient.getAuthorizationGrantTypes() != null) {
            clientInfoVo.setAuthorizedGrantTypes(registeredClient.getAuthorizationGrantTypes()
                    .stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet()));
        }
        if (registeredClient.getRedirectUris() != null) {
            clientInfoVo.setRedirectUris(registeredClient.getRedirectUris());
        }
        if (registeredClient.getPostLogoutRedirectUris() != null) {
            clientInfoVo.setPostLogoutRedirectUris(registeredClient.getPostLogoutRedirectUris());
        }
        if (registeredClient.getScopes() != null) {
            clientInfoVo.setScopes(registeredClient.getScopes());
        }
        if (registeredClient.getClientSettings() != null) {
            clientInfoVo.setClientSettings(registeredClient.getClientSettings().getSettings());
        }
        if (registeredClient.getTokenSettings() != null) {
            clientInfoVo.setTokenSettings(registeredClient.getTokenSettings().getSettings());
        }
    }

    @Override
    public RegisteredClient reverse(ClientInfoVo clientInfoVo) {
        RegisteredClient.Builder builder = RegisteredClient.withId(clientInfoVo.getId().toString())
                .clientId(clientInfoVo.getClientId())
                .clientIdIssuedAt(clientInfoVo.getClientIdIssuedAt().toInstant())
                .clientSecret(clientInfoVo.getClientSecret())
                .clientSecretExpiresAt(clientInfoVo.getClientSecretExpiresAt().toInstant())
                .clientName(clientInfoVo.getClientName());
        if (clientInfoVo.getClientAuthenticationMethods() != null) {
            clientInfoVo.getClientAuthenticationMethods().forEach(method -> builder.clientAuthenticationMethod(new ClientAuthenticationMethod(method)));
        }
        if (clientInfoVo.getAuthorizedGrantTypes() != null) {
            clientInfoVo.getAuthorizedGrantTypes().forEach(type -> builder.authorizationGrantType(new AuthorizationGrantType(type)));
        }
        if (clientInfoVo.getRedirectUris() != null) {
            clientInfoVo.getRedirectUris().forEach(builder::redirectUri);
        }
        if (clientInfoVo.getPostLogoutRedirectUris() != null) {
            clientInfoVo.getPostLogoutRedirectUris().forEach(builder::postLogoutRedirectUri);
        }
        if (clientInfoVo.getScopes() != null) {
            clientInfoVo.getScopes().forEach(builder::scope);
        }
        builder.clientSettings(ClientSettings.withSettings(clientInfoVo.getClientSettings()).build());
        builder.tokenSettings(TokenSettings.withSettings(clientInfoVo.getTokenSettings()).build());
        return builder.build();
    }

    @Override
    public void reverseCopy(ClientInfoVo clientInfoVo, RegisteredClient registeredClient) {
        throw new UnsupportedOperationException("reverseCopy");
    }
}
