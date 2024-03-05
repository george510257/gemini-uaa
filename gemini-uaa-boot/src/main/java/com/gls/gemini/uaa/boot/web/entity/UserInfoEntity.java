package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户信息表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_info")
public class UserInfoEntity extends BaseEntity {

    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_MOBILE = "mobile";
    public static final String COL_EMAIL = "email";
    public static final String COL_REAL_NAME = "real_name";
    public static final String COL_NICK_NAME = "nick_name";
    public static final String COL_AVATAR = "avatar";
    public static final String COL_LANGUAGE = "language";
    public static final String COL_LOCALE = "locale";
    public static final String COL_TIME_ZONE = "time_zone";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 语言
     */
    private String language;
    /**
     * 区域
     */
    private String locale;
    /**
     * 时区
     */
    private String timeZone;
}
