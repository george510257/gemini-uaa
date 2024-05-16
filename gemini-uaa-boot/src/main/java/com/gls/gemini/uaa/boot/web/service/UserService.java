package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.sdk.core.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

public class UserService implements UserDetailsManager, UserDetailsPasswordService {

    @Override
    public UserDto updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDto findById(Long userId) {
        return null;
    }
}
