package com.gls.gemini.uaa.boot.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.stereotype.Component;

@Component
@Order(12)
public class UaaJackson2Module extends SimpleModule {

    public UaaJackson2Module() {
        super(UaaJackson2Module.class.getName(), new Version(1, 0, 0, null, "com.gls.gemini.uaa", "gemini-uaa-boot"));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(OAuth2Authorization.class, OAuth2AuthorizationMixin.class);
        context.setMixInAnnotations(OAuth2AuthorizationConsent.class, OAuth2AuthorizationConsentMixin.class);
        context.setMixInAnnotations(AuthorizationGrantType.class, AuthorizationGrantTypeMixin.class);
    }
}
