package com.gls.gemini.uaa.boot.jackson2.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gls.gemini.starter.json.util.Jackson2Util;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;

import java.io.IOException;
import java.util.Set;

/**
 * OAuth2AuthorizationConsent 反序列化器
 */
public class OAuth2AuthorizationConsentDeserializer extends JsonDeserializer<OAuth2AuthorizationConsent> {
    /**
     * Set 类型的 TypeReference 对象
     */
    private static final TypeReference<Set<SimpleGrantedAuthority>> SET_TYPE_REFERENCE = new TypeReference<>() {
    };

    /**
     * 反序列化 OAuth2AuthorizationConsent 对象
     *
     * @param parser  JsonParser 对象
     * @param context DeserializationContext 对象
     * @return OAuth2AuthorizationConsent 对象
     * @throws IOException IO异常
     */
    @Override
    public OAuth2AuthorizationConsent deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        // 获取ObjectMapper
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        // 获取JsonNode
        JsonNode node = mapper.readTree(parser);
        // 获取JsonNode中的字段值
        String registeredClientId = Jackson2Util.findStringValue(node, "registeredClientId");
        String principalName = Jackson2Util.findStringValue(node, "principalName");
        Set<? extends GrantedAuthority> authorities = Jackson2Util.findValue(node, "authorities", SET_TYPE_REFERENCE, mapper);

        // 构建OAuth2AuthorizationConsent对象
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(registeredClientId, principalName);
        // 设置authorities
        authorities.forEach(builder::authority);
        // 返回OAuth2AuthorizationConsent对象
        return builder.build();
    }

}
