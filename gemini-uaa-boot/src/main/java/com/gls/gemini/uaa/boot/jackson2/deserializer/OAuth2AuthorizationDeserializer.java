package com.gls.gemini.uaa.boot.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gls.gemini.starter.json.util.Jackson2Util;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * OAuth2Authorization 反序列化器
 */
public class OAuth2AuthorizationDeserializer extends JsonDeserializer<OAuth2Authorization> {
    /**
     * Map 类型的 TypeReference 对象
     */
    private static final TypeReference<Map<String, OAuth2Authorization.Token<?>>> TOKENS = new TypeReference<>() {
    };

    /**
     * 反序列化 OAuth2Authorization 对象
     *
     * @param parser  JsonParser 对象
     * @param context DeserializationContext 对象
     * @return OAuth2Authorization 对象
     * @throws IOException      IO异常
     * @throws JacksonException Jackson异常
     */
    @Override
    public OAuth2Authorization deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        // 获取ObjectMapper
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        // 获取JsonNode
        JsonNode node = mapper.readTree(parser);
        // 获取JsonNode中的字段值
        String id = Jackson2Util.findStringValue(node, "id");
        // 注册的客户端ID
        String registeredClientId = Jackson2Util.findStringValue(node, "registeredClientId");
        // 主体名称
        String principalName = Jackson2Util.findStringValue(node, "principalName");
        // 授权类型
        AuthorizationGrantType authorizationGrantType = Jackson2Util.findValue(node, "authorizationGrantType", AuthorizationGrantType.class, mapper);
        // 授权范围
        Set<String> authorizedScopes = Jackson2Util.findValue(node, "authorizedScopes", Jackson2Util.SET_STRING_TYPE_REFERENCE, mapper);
        // tokens
        Map<String, OAuth2Authorization.Token<?>> tokens = Jackson2Util.findValue(node, "tokens", TOKENS, mapper);
        // attributes
        Map<String, Object> attributes = Jackson2Util.findValue(node, "attributes", Jackson2Util.MAP_STRING_OBJECT_TYPE_REFERENCE, mapper);

        // 构建OAuth2Authorization对象
        OAuth2Authorization.Builder builder = OAuth2Authorization.withRegisteredClient(RegisteredClient.withId(registeredClientId)
                        .clientId("test-client-id")
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .build())
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
