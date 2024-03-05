package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 授权信息表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_authorization_info")
public class AuthorizationInfoEntity extends BaseEntity {

    public static final String COL_CLIENT_ID = "client_id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_GRANT_TYPE = "grant_type";
    public static final String COL_SCOPES = "scopes";
    public static final String COL_ATTRIBUTES = "attributes";
    public static final String COL_CONSENT = "consent";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 客户端id
     */
    private Long clientId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 授权类型
     */
    private String grantType;
    /**
     * 作用域
     */
    private String scopes;
    /**
     * 属性
     */
    private String attributes;
    /**
     * 是否同意 0:否 1:是
     */
    private Integer consent;
}
