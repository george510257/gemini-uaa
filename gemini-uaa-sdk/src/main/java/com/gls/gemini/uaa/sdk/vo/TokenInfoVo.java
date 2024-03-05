package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * token信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "tokenInfo", description = "token信息表Vo")
public class TokenInfoVo extends BaseVo {

    /**
     * token值
     */
    @ExcelProperty(value = "token值")
    @Schema(title = "token值", description = "token值")
    private String tokenValue;
    /**
     * token类型 token_type=authorization_code|access_token|refresh_token|oidc_id_token|user_code|device_code
     */
    @ExcelProperty(value = "token类型 token_type=authorization_code|access_token|refresh_token|oidc_id_token|user_code|device_code")
    @Schema(title = "token类型 token_type=authorization_code|access_token|refresh_token|oidc_id_token|user_code|device_code", description = "token类型 token_type=authorization_code|access_token|refresh_token|oidc_id_token|user_code|device_code")
    private String tokenType;
    /**
     * 发放时间
     */
    @ExcelProperty(value = "发放时间")
    @Schema(title = "发放时间", description = "发放时间")
    private Date issuedAt;
    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    @Schema(title = "过期时间", description = "过期时间")
    private Date expiresAt;
    /**
     * 元数据
     */
    @ExcelProperty(value = "元数据")
    @Schema(title = "元数据", description = "元数据")
    private String metadata;
    /**
     * 访问token类型 token_type=access_token时有效
     */
    @ExcelProperty(value = "访问token类型 token_type=access_token时有效")
    @Schema(title = "访问token类型 token_type=access_token时有效", description = "访问token类型 token_type=access_token时有效")
    private String accessTokenType;
    /**
     * 访问token作用域 token_type=access_token时有效
     */
    @ExcelProperty(value = "访问token作用域 token_type=access_token时有效")
    @Schema(title = "访问token作用域 token_type=access_token时有效", description = "访问token作用域 token_type=access_token时有效")
    private String accessTokenScopes;
    /**
     * 授权id
     */
    @ExcelProperty(value = "授权id")
    @Schema(title = "授权id", description = "授权id")
    private Long authorizationId;
}
