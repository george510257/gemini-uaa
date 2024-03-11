package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.uaa.boot.web.converter.UserConverter;
import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsManager {
    @Resource
    private UserInfoFeign userInfoFeign;
    @Resource
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoFeign.loadUserByUsername(username).getData();
    }

    @Override
    public void createUser(UserDetails user) {
        if (user != null) {
            userInfoFeign.insert(userConverter.convert((User) user));
        }
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException("updateUser");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("deleteUser");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("changePassword");
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}
