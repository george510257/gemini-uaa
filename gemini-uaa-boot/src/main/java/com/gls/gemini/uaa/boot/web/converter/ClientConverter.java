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
 * 客户端转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientConverter extends BaseConverter<RegisteredClient, ClientInfoVo> {
    @Mappings({
            @Mapping(target = "clientAuthenticationMethods", expression = "java(convertClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods()))"),
            @Mapping(target = "authorizedGrantTypes", expression = "java(convertAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes()))"),
            @Mapping(target = "clientSettings", expression = "java(convertClientSettings(registeredClient.getClientSettings()))"),
            @Mapping(target = "tokenSettings", expression = "java(convertTokenSettings(registeredClient.getTokenSettings()))")
    })
    @Override
    ClientInfoVo convert(RegisteredClient registeredClient);

    @Override
    void convertCopy(RegisteredClient registeredClient, ClientInfoVo clientInfoVo);

    @Override
    RegisteredClient reverse(ClientInfoVo clientInfoVo);

    @Override
    void reverseCopy(ClientInfoVo clientInfoVo, RegisteredClient registeredClient);

    default Set<String> convertClientAuthenticationMethods(Set<ClientAuthenticationMethod> clientAuthenticationMethods) {
        return clientAuthenticationMethods.stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toSet());
    }

    default Set<ClientAuthenticationMethod> reverseClientAuthenticationMethods(Set<String> clientAuthenticationMethods) {
        return clientAuthenticationMethods.stream().map(ClientAuthenticationMethod::new).collect(Collectors.toSet());
    }

    default Set<String> convertAuthorizationGrantTypes(Set<AuthorizationGrantType> authorizationGrantTypes) {
        return authorizationGrantTypes.stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet());
    }

    default Set<AuthorizationGrantType> reverseAuthorizationGrantTypes(Set<String> authorizationGrantTypes) {
        return authorizationGrantTypes.stream().map(AuthorizationGrantType::new).collect(Collectors.toSet());
    }

    default Map<String, Object> convertClientSettings(ClientSettings clientSettings) {
        return clientSettings.getSettings();
    }

    default ClientSettings reverseClientSettings(Map<String, Object> clientSettings) {
        return ClientSettings.withSettings(clientSettings).build();
    }

    default Map<String, Object> convertTokenSettings(TokenSettings tokenSettings) {
        return tokenSettings.getSettings();
    }

    default TokenSettings reverseTokenSettings(Map<String, Object> tokenSettings) {
        return TokenSettings.withSettings(tokenSettings).build();
    }
}
