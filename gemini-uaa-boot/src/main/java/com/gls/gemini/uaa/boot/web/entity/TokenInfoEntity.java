package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * token信息表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_token_info")
public class TokenInfoEntity extends BaseEntity {

    public static final String COL_TOKEN_VALUE = "token_value";
    public static final String COL_TOKEN_TYPE = "token_type";
    public static final String COL_ISSUED_AT = "issued_at";
    public static final String COL_EXPIRES_AT = "expires_at";
    public static final String COL_METADATA = "metadata";
    public static final String COL_ACCESS_TOKEN_TYPE = "access_token_type";
    public static final String COL_ACCESS_TOKEN_SCOPES = "access_token_scopes";
    public static final String COL_AUTHORIZATION_ID = "authorization_id";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * token值
     */
    private String tokenValue;
    /**
     * token类型 token_type=authorization_code|access_token|refresh_token|oidc_id_token|user_code|device_code
     */
    private String tokenType;
    /**
     * 发放时间
     */
    private Date issuedAt;
    /**
     * 过期时间
     */
    private Date expiresAt;
    /**
     * 元数据
     */
    private String metadata;
    /**
     * 访问token类型 token_type=access_token时有效
     */
    private String accessTokenType;
    /**
     * 访问token作用域 token_type=access_token时有效
     */
    private String accessTokenScopes;
    /**
     * 授权id
     */
    private Long authorizationId;
}
