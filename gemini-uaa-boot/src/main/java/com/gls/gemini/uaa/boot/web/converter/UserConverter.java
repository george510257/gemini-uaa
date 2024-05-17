package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.sdk.core.dto.RoleDto;
import com.gls.gemini.sdk.core.dto.UserDto;
import com.gls.gemini.upms.sdk.vo.UserInfoVo;
import org.mapstruct.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 用户信息表 转换器
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserConverter implements BaseConverter<UserDto, UserInfoVo> {

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "roles", expression = "java(getRoles(user.getAuthorities()))"),
    })
    public abstract UserDto convert(UserDetails user);

    @Named("getRoles")
    protected List<RoleDto> getRoles(Collection<? extends GrantedAuthority> authorities) {
        return null;
    }
}
