package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.uaa.boot.web.service.UserService;
import com.gls.gemini.upms.sdk.vo.AuthorizationConsentInfoVo;
import jakarta.annotation.Resource;
import org.mapstruct.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class AuthorizationConsentConverter implements BaseConverter<OAuth2AuthorizationConsent, AuthorizationConsentInfoVo> {
    @Resource
    private UserService userService;

    @Mappings({
            @Mapping(target = "clientId", source = "registeredClientId"),
            @Mapping(target = "userId", expression = "java(getUserId(authorizationConsent.getPrincipalName()))"),
            @Mapping(target = "authorities", expression = "java(getAuthorities(authorizationConsent.getAuthorities()))"),
    })
    @Override
    public abstract AuthorizationConsentInfoVo convert(OAuth2AuthorizationConsent authorizationConsent);

    @InheritConfiguration(name = "convert")
    @Override
    public abstract AuthorizationConsentInfoVo convertCopy(OAuth2AuthorizationConsent authorizationConsent, @MappingTarget AuthorizationConsentInfoVo vo);

    @Override
    public OAuth2AuthorizationConsent reverse(AuthorizationConsentInfoVo vo) {
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.withId(vo.getClientId().toString(), getPrincipalName(vo.getUserId()));
        return reverseByBuilder(vo, builder);
    }

    @Override
    public OAuth2AuthorizationConsent reverseCopy(AuthorizationConsentInfoVo vo, OAuth2AuthorizationConsent authorizationConsent) {
        OAuth2AuthorizationConsent.Builder builder = OAuth2AuthorizationConsent.from(authorizationConsent);
        return reverseByBuilder(vo, builder);
    }

    private OAuth2AuthorizationConsent reverseByBuilder(AuthorizationConsentInfoVo vo, OAuth2AuthorizationConsent.Builder builder) {
        for (String authority : vo.getAuthorities()) {
            builder.authority(new SimpleGrantedAuthority(authority));
        }
        return builder.build();
    }

    @Named("getUserId")
    protected Long getUserId(String principalName) {
        return userService.loadUserByUsername(principalName).getId();
    }

    @Named("getAuthorities")
    protected Set<String> getAuthorities(Set<GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    private String getPrincipalName(Long userId) {
        return userService.findById(userId).getUsername();
    }
}
