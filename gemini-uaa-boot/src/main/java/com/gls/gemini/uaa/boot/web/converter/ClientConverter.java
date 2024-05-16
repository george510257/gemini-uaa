package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.upms.sdk.vo.ClientInfoVo;
import org.mapstruct.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 客户端信息表 转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ClientConverter implements BaseConverter<RegisteredClient, ClientInfoVo> {

    @Mappings({
            @Mapping(target = "clientId", source = "clientId"),
            @Mapping(target = "clientIdIssuedAt", source = "clientIdIssuedAt"),
            @Mapping(target = "clientSecret", source = "clientSecret"),
            @Mapping(target = "clientSecretExpiresAt", source = "clientSecretExpiresAt"),
            @Mapping(target = "clientName", source = "clientName"),
            @Mapping(target = "clientAuthenticationMethods", expression = "java(convertClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods()))"),
            @Mapping(target = "authorizedGrantTypes", expression = "java(convertAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes()))"),
            @Mapping(target = "redirectUris", source = "redirectUris"),
            @Mapping(target = "postLogoutRedirectUris", source = "postLogoutRedirectUris"),
            @Mapping(target = "scopes", source = "scopes"),
            @Mapping(target = "clientSettings", expression = "java(convertClientSettings(registeredClient.getClientSettings()))"),
            @Mapping(target = "tokenSettings", expression = "java(convertTokenSettings(registeredClient.getTokenSettings()))")
    })
    @Override
    public abstract ClientInfoVo convert(RegisteredClient registeredClient);

    @InheritConfiguration(name = "convert")
    @Override
    public abstract ClientInfoVo convertCopy(RegisteredClient registeredClient, @MappingTarget ClientInfoVo clientInfoVo);

    @Override
    public RegisteredClient reverse(ClientInfoVo clientInfoVo) {
        RegisteredClient.Builder builder = RegisteredClient.withId(String.valueOf(clientInfoVo.getId()));
        return reverseByBuilder(clientInfoVo, builder);

    }

    @Override
    public RegisteredClient reverseCopy(ClientInfoVo clientInfoVo, RegisteredClient registeredClient) {
        RegisteredClient.Builder builder = RegisteredClient.from(registeredClient);
        return reverseByBuilder(clientInfoVo, builder);
    }

    private RegisteredClient reverseByBuilder(ClientInfoVo clientInfoVo, RegisteredClient.Builder builder) {
        return builder.clientId(clientInfoVo.getClientId())
                .clientIdIssuedAt(clientInfoVo.getClientIdIssuedAt().toInstant())
                .clientSecret(clientInfoVo.getClientSecret())
                .clientSecretExpiresAt(clientInfoVo.getClientSecretExpiresAt().toInstant())
                .clientName(clientInfoVo.getClientName())
                .clientAuthenticationMethods(methods -> methods.addAll(reverseClientAuthenticationMethods(clientInfoVo.getClientAuthenticationMethods())))
                .authorizationGrantTypes(grantTypes -> grantTypes.addAll(reverseAuthorizationGrantTypes(clientInfoVo.getAuthorizedGrantTypes())))
                .redirectUris(uris -> uris.addAll(clientInfoVo.getRedirectUris()))
                .postLogoutRedirectUris(uris -> uris.addAll(clientInfoVo.getPostLogoutRedirectUris()))
                .scopes(scopes -> scopes.addAll(clientInfoVo.getScopes()))
                .clientSettings(reverseClientSettings(clientInfoVo.getClientSettings()))
                .tokenSettings(reverseTokenSettings(clientInfoVo.getTokenSettings()))
                .build();
    }

    @Named("convertClientAuthenticationMethods")
    protected Set<String> convertClientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods) {
        return clientAuthenticationMethods.stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toSet());
    }

    @Named("convertAuthorizationGrantTypes")
    protected Set<String> convertAuthorizationGrantTypes(Set<AuthorizationGrantType> authorizedGrantTypes) {
        return authorizedGrantTypes.stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet());
    }

    @Named("convertClientSettings")
    protected Map<String, Object> convertClientSettings(ClientSettings clientSettings) {
        return clientSettings.getSettings();
    }

    @Named("convertTokenSettings")
    protected Map<String, Object> convertTokenSettings(TokenSettings tokenSettings) {
        return tokenSettings.getSettings();
    }

    private Set<ClientAuthenticationMethod> reverseClientAuthenticationMethods(Set<String> clientAuthenticationMethods) {
        return clientAuthenticationMethods.stream().map(ClientAuthenticationMethod::new).collect(Collectors.toSet());
    }

    private Set<AuthorizationGrantType> reverseAuthorizationGrantTypes(Set<String> authorizedGrantTypes) {
        return authorizedGrantTypes.stream().map(AuthorizationGrantType::new).collect(Collectors.toSet());
    }

    private ClientSettings reverseClientSettings(Map<String, Object> clientSettings) {
        return ClientSettings.withSettings(clientSettings).build();
    }

    private TokenSettings reverseTokenSettings(Map<String, Object> tokenSettings) {
        return TokenSettings.withSettings(tokenSettings).build();
    }
}
