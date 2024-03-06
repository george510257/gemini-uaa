package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserInfoFeign userInfoFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoFeign.loadUserByUsername(username);
    }
}
