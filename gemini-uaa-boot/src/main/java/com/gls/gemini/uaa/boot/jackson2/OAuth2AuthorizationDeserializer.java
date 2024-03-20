package com.gls.gemini.uaa.boot.jackson2;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gls.gemini.starter.json.util.Jackson2Util;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class OAuth2AuthorizationDeserializer extends JsonDeserializer<OAuth2Authorization> {

    private static final TypeReference<AuthorizationGrantType> AUTHORIZATION_GRANT_TYPE = new TypeReference<>() {
    };

    private static final TypeReference<Map<Class<? extends OAuth2Token>, OAuth2Authorization.Token<?>>> TOKENS = new TypeReference<>() {
    };

    @Override
    public OAuth2Authorization deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        // 获取ObjectMapper
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        // 获取JsonNode
        JsonNode node = mapper.readTree(parser);
        // 获取JsonNode中的字段值
        String id = Jackson2Util.findStringValue(node, "id");
        String registeredClientId = Jackson2Util.findStringValue(node, "registeredClientId");
        String principalName = Jackson2Util.findStringValue(node, "principalName");
        AuthorizationGrantType authorizationGrantType = Jackson2Util.findValue(node, "authorizationGrantType", AUTHORIZATION_GRANT_TYPE, mapper);
        Set<String> authorizedScopes = Jackson2Util.findValue(node, "authorizedScopes", Jackson2Util.SET_STRING_TYPE_REFERENCE, mapper);
        Map<Class<? extends OAuth2Token>, OAuth2Authorization.Token<?>> tokens = Jackson2Util.findValue(node, "tokens", TOKENS, mapper);
        Map<String, Object> attributes = Jackson2Util.findValue(node, "attributes", Jackson2Util.MAP_STRING_OBJECT_TYPE_REFERENCE, mapper);

        // 构建OAuth2Authorization对象
        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(RegisteredClient.withId(registeredClientId).build())
                .id(id)
                .principalName(principalName)
                .authorizationGrantType(authorizationGrantType)
                .authorizedScopes(authorizedScopes);
        // 设置tokens
        tokens.forEach((key, value) -> builder.token(value.getToken()));
        // 设置attributes
        attributes.forEach(builder::attribute);
        // 返回OAuth2Authorization对象
        return builder.build();
    }

}
