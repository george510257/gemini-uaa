package com.gls.gemini.uaa.boot.config;

import cn.hutool.core.lang.UUID;
import com.gls.gemini.sdk.core.vo.UserVo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.Locale;
import java.util.TimeZone;

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
        UserVo UserVo = new UserVo();
        UserVo.setUsername("admin");
        UserVo.setPassword("{noop}admin");
        UserVo.setEmail("admin@gls.com");
        UserVo.setMobile("18888888888");
        UserVo.setRealName("管理员");
        UserVo.setNickName("admin");
        UserVo.setAvatar("https://gitee.com/gemini-arch/gemini-arch/raw/main/gemini-arch.png");
        UserVo.setLanguage("zh-CN");
        UserVo.setLocale(Locale.CHINA);
        UserVo.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        UserDetails user = userDetailsService.loadUserByUsername("admin");
        if (user == null) {
            userDetailsService.createUser(UserVo);
        }
    }
}
