package com.gls.gemini.uaa.boot.jackson2.mixin;

import com.fasterxml.jackson.annotation.*;

import java.time.Instant;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuth2AuthorizationCodeMixin {

    @JsonCreator
    public OAuth2AuthorizationCodeMixin(@JsonProperty("tokenValue") String tokenValue,
                                        @JsonProperty("issuedAt") Instant issuedAt,
                                        @JsonProperty("expiresAt") Instant expiresAt) {
    }
}
