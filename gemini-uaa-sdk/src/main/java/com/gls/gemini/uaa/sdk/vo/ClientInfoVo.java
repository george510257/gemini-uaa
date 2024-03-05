package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 客户端信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "clientInfo", description = "客户端信息表Vo")
public class ClientInfoVo extends BaseVo {

    /**
     * 客户端id
     */
    @ExcelProperty(value = "客户端id")
    @Schema(title = "客户端id", description = "客户端id")
    private String clientId;
    /**
     * 客户端密钥
     */
    @ExcelProperty(value = "客户端密钥")
    @Schema(title = "客户端密钥", description = "客户端密钥")
    private String clientSecret;
    /**
     * 客户端名称
     */
    @ExcelProperty(value = "客户端名称")
    @Schema(title = "客户端名称", description = "客户端名称")
    private String clientName;
    /**
     * 客户端认证方式
     */
    @ExcelProperty(value = "客户端认证方式")
    @Schema(title = "客户端认证方式", description = "客户端认证方式")
    private String clientAuthenticationMethods;
    /**
     * 授权类型
     */
    @ExcelProperty(value = "授权类型")
    @Schema(title = "授权类型", description = "授权类型")
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    @ExcelProperty(value = "重定向地址")
    @Schema(title = "重定向地址", description = "重定向地址")
    private String redirectUris;
    /**
     * 登出重定向地址
     */
    @ExcelProperty(value = "登出重定向地址")
    @Schema(title = "登出重定向地址", description = "登出重定向地址")
    private String postLogoutRedirectUris;
    /**
     * 作用域
     */
    @ExcelProperty(value = "作用域")
    @Schema(title = "作用域", description = "作用域")
    private String scopes;
    /**
     * 客户端设置
     */
    @ExcelProperty(value = "客户端设置")
    @Schema(title = "客户端设置", description = "客户端设置")
    private String clientSettings;
    /**
     * token设置
     */
    @ExcelProperty(value = "token设置")
    @Schema(title = "token设置", description = "token设置")
    private String tokenSettings;
    /**
     * 附加信息
     */
    @ExcelProperty(value = "附加信息")
    @Schema(title = "附加信息", description = "附加信息")
    private String additionalInformation;
    /**
     * 客户端id发放时间
     */
    @ExcelProperty(value = "客户端id发放时间")
    @Schema(title = "客户端id发放时间", description = "客户端id发放时间")
    private Date clientIdIssuedAt;
    /**
     * 客户端密钥过期时间
     */
    @ExcelProperty(value = "客户端密钥过期时间")
    @Schema(title = "客户端密钥过期时间", description = "客户端密钥过期时间")
    private Date clientSecretExpiresAt;
}
