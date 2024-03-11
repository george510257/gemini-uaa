package com.gls.gemini.uaa.boot.config;

import cn.hutool.core.lang.UUID;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * Uaa配置
 */
@Configuration
public class UaaConfig {
    @Resource
    private RegisteredClientRepository registeredClientRepository;
    @Resource
    private UserDetailsManager userDetailsService;

    @PostConstruct
    public void initClient() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("messaging-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
                .redirectUri("https://www.baidu.com")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .scope("message:read")
                .scope("message:write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        RegisteredClient messageClient = registeredClientRepository.findByClientId("messaging-client");
        if (messageClient == null) {
            registeredClientRepository.save(registeredClient);
        }
        RegisteredClient registeredClient2 = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("device-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.DEVICE_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .scope("message:read")
                .scope("message:write")
                .build();
        RegisteredClient deviceClient = registeredClientRepository.findByClientId("device-client");
        if (deviceClient == null) {
            registeredClientRepository.save(registeredClient2);
        }
    }

    @PostConstruct
    public void initUser() {
        UserDetails userDetails = User.withUsername("user")
                .password("{noop}password")
                .roles("admin", "user")
                .authorities("message:read", "message:write")
                .build();
        UserDetails user = userDetailsService.loadUserByUsername("user");
        if (user == null) {
            userDetailsService.createUser(userDetails);
        }
    }
}
