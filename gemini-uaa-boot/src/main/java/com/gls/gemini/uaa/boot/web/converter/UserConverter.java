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

    public UserDto convertByUserDetails(UserDetails user) {
        if (user instanceof UserDto userDto) {
            return userDto;
        }
        return convertFromUserDetails(user);
    }

    @Mappings({
            @Mapping(target = "username", source = "username"),
            @Mapping(target = "password", source = "password"),
            @Mapping(target = "authorities", ignore = true),
            @Mapping(target = "roles", expression = "java(getRoles(user.getAuthorities()))"),
    })
    public abstract UserDto convertFromUserDetails(UserDetails user);

    @Named("getRoles")
    protected List<RoleDto> getRoles(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(authority -> {
            if (authority instanceof RoleDto roleDto) {
                return roleDto;
            }
            RoleDto roleDto = new RoleDto();
            roleDto.setCode(authority.getAuthority());
            return roleDto;
        }).toList();
    }
}
