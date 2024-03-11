package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.upms.sdk.vo.UserInfoVo;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements BaseConverter<User, UserInfoVo> {

    @Override
    public UserInfoVo convert(User user) {
        UserInfoVo userInfoVo = new UserInfoVo();
        this.convertCopy(user, userInfoVo);
        return userInfoVo;
    }

    @Override
    public void convertCopy(User user, UserInfoVo userInfoVo) {
        if (user == null || userInfoVo == null) {
            return;
        }
        if (user.getUsername() != null) {
            userInfoVo.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            userInfoVo.setPassword(user.getPassword());
        }
    }

    @Override
    public User reverse(UserInfoVo userInfoVo) {
        return null;
    }

    @Override
    public void reverseCopy(UserInfoVo userInfoVo, User user) {

    }
}
