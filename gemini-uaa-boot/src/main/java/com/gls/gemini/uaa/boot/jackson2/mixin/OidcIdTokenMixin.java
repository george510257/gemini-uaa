package com.gls.gemini.uaa.boot.jackson2.mixin;

import com.fasterxml.jackson.annotation.*;

import java.time.Instant;
import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OidcIdTokenMixin {

    @JsonCreator
    public OidcIdTokenMixin(@JsonProperty("tokenValue") String tokenValue,
                            @JsonProperty("issuedAt") Instant issuedAt,
                            @JsonProperty("expiresAt") Instant expiresAt,
                            @JsonProperty("claims") Map<String, Object> claims) {
    }
}
