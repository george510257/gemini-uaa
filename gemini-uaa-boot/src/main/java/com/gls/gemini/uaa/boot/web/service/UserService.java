package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.sdk.core.vo.UserVo;
import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsManager {
    @Resource
    private UserInfoFeign userInfoFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoFeign.loadUserByUsername(username).getData();
    }

    @Override
    public void createUser(UserDetails user) {
        if (user != null) {
            if (user instanceof UserVo userVo) {
                userInfoFeign.saveUser(userVo);
            } else {
                throw new UnsupportedOperationException("createUser");
            }

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
