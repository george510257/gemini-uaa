package com.gls.gemini.uaa.boot.jackson2;

import com.fasterxml.jackson.annotation.*;

import java.time.Instant;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE, creatorVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class OAuth2AuthorizationCodeMixin {

    @JsonCreator
    OAuth2AuthorizationCodeMixin(@JsonProperty("tokenValue") String tokenValue, @JsonProperty("issuedAt") Instant issuedAt, @JsonProperty("expiresAt") Instant expiresAt) {
    }
}
