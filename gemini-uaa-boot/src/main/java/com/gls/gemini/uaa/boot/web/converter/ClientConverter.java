package com.gls.gemini.uaa.boot.web.converter;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.map.MapUtil;
import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.upms.sdk.vo.ClientInfoVo;
import org.mapstruct.*;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
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
            @Mapping(target = "id", source = "id"),
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
        ClientSettings.Builder builder = ClientSettings.builder();
        if (clientSettings.containsKey(ConfigurationSettingNames.Client.REQUIRE_PROOF_KEY)) {
            builder.requireProofKey(MapUtil.getBool(clientSettings, ConfigurationSettingNames.Client.REQUIRE_PROOF_KEY));
        }
        if (clientSettings.containsKey(ConfigurationSettingNames.Client.REQUIRE_AUTHORIZATION_CONSENT)) {
            builder.requireAuthorizationConsent(MapUtil.getBool(clientSettings, ConfigurationSettingNames.Client.REQUIRE_AUTHORIZATION_CONSENT));
        }
        if (clientSettings.containsKey(ConfigurationSettingNames.Client.JWK_SET_URL)) {
            builder.jwkSetUrl(MapUtil.getStr(clientSettings, ConfigurationSettingNames.Client.JWK_SET_URL));
        }
        if (clientSettings.containsKey(ConfigurationSettingNames.Client.TOKEN_ENDPOINT_AUTHENTICATION_SIGNING_ALGORITHM)) {
            builder.tokenEndpointAuthenticationSigningAlgorithm(SignatureAlgorithm.from(MapUtil.getStr(clientSettings, ConfigurationSettingNames.Client.TOKEN_ENDPOINT_AUTHENTICATION_SIGNING_ALGORITHM)));
        }
        return builder.build();
    }

    private TokenSettings reverseTokenSettings(Map<String, Object> tokenSettings) {
        TokenSettings.Builder builder = TokenSettings.builder();
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.AUTHORIZATION_CODE_TIME_TO_LIVE)) {
            builder.authorizationCodeTimeToLive(MapUtil.get(tokenSettings, ConfigurationSettingNames.Token.AUTHORIZATION_CODE_TIME_TO_LIVE, Duration.class));
        }
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE)) {
            builder.accessTokenTimeToLive(MapUtil.get(tokenSettings, ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE, Duration.class));
        }
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT)) {
            builder.accessTokenFormat(getAccessTokenFormat(tokenSettings));
        }
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.DEVICE_CODE_TIME_TO_LIVE)) {
            builder.deviceCodeTimeToLive(MapUtil.get(tokenSettings, ConfigurationSettingNames.Token.DEVICE_CODE_TIME_TO_LIVE, Duration.class));
        }
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.REUSE_REFRESH_TOKENS)) {
            builder.reuseRefreshTokens(MapUtil.getBool(tokenSettings, ConfigurationSettingNames.Token.REUSE_REFRESH_TOKENS));
        }
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE)) {
            builder.refreshTokenTimeToLive(MapUtil.get(tokenSettings, ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE, Duration.class));
        }
        if (tokenSettings.containsKey(ConfigurationSettingNames.Token.ID_TOKEN_SIGNATURE_ALGORITHM)) {
            builder.idTokenSignatureAlgorithm(SignatureAlgorithm.from(MapUtil.getStr(tokenSettings, ConfigurationSettingNames.Token.ID_TOKEN_SIGNATURE_ALGORITHM)));
        }
        return builder.build();
    }

    private OAuth2TokenFormat getAccessTokenFormat(Map<String, Object> tokenSettings) {
        Map<String, Object> map = MapUtil.get(tokenSettings, ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT, new TypeReference<>() {
        });
        return new OAuth2TokenFormat(MapUtil.getStr(map, "value"));
    }
}
