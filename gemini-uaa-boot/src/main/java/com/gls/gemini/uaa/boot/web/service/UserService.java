package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.sdk.core.dto.UserDto;
import com.gls.gemini.uaa.boot.web.converter.UserConverter;
import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsManager, UserDetailsPasswordService {

    @Resource
    private UserInfoFeign userInfoFeign;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserConverter userConverter;

    @Override
    public UserDto updatePassword(UserDetails user, String newPassword) {
        UserDto userDto = loadUserByUsername(user.getUsername());
        userDto.setPassword(newPassword);
        userInfoFeign.update(userDto.getId(), userConverter.convert(userDto));
        return userDto;
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername())) {
            throw new IllegalArgumentException("用户已存在");
        }
        UserDto userDto = userConverter.convertByUserDetails(user);
        userInfoFeign.insert(userConverter.convert(userDto));
    }

    @Override
    public void updateUser(UserDetails user) {
        UserDto userDto = userConverter.convertByUserDetails(user);
        userInfoFeign.update(userDto.getId(), userConverter.convert(userDto));
    }

    @Override
    public void deleteUser(String username) {
        UserDto userDto = loadUserByUsername(username);
        userInfoFeign.delete(userDto.getId());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            UserDto userDto = loadUserByUsername(userDetails.getUsername());
            if (!passwordEncoder.matches(oldPassword, userDto.getPassword())) {
                throw new IllegalArgumentException("原密码错误");
            }
            userDto.setPassword(passwordEncoder.encode(newPassword));
            userInfoFeign.update(userDto.getId(), userConverter.convert(userDto));
        }
    }

    @Override
    public boolean userExists(String username) {
        UserDto userDto = loadUserByUsername(username);
        return userDto != null;
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoFeign.loadUserByUsername(username).getData();
    }

    public UserDto findById(Long userId) {
        return userConverter.reverse(userInfoFeign.get(userId).getData());
    }
}
