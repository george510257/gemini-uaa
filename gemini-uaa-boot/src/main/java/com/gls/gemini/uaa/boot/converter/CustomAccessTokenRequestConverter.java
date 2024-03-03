package com.gls.gemini.uaa.boot.converter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class CustomAccessTokenRequestConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        return null;
    }
}
