package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.sdk.core.vo.UserVo;
import com.gls.gemini.upms.sdk.feign.UserInfoFeign;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService implements UserDetailsService {
    @Resource
    private UserInfoFeign userInfoFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        UserVo userVo = userInfoFeign.loadUserByUsername(username).getData();
        if (userVo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        log.info("userVo: {}", userVo);
        return userVo;
    }
}
