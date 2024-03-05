package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 授权信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "authorizationInfo", description = "授权信息表Vo")
public class AuthorizationInfoVo extends BaseVo {

    /**
     * 客户端id
     */
    @ExcelProperty(value = "客户端id")
    @Schema(title = "客户端id", description = "客户端id")
    private Long clientId;
    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    @Schema(title = "用户id", description = "用户id")
    private Long userId;
    /**
     * 授权类型
     */
    @ExcelProperty(value = "授权类型")
    @Schema(title = "授权类型", description = "授权类型")
    private String grantType;
    /**
     * 作用域
     */
    @ExcelProperty(value = "作用域")
    @Schema(title = "作用域", description = "作用域")
    private String scopes;
    /**
     * 属性
     */
    @ExcelProperty(value = "属性")
    @Schema(title = "属性", description = "属性")
    private String attributes;
    /**
     * 是否同意 0:否 1:是
     */
    @ExcelProperty(value = "是否同意 0:否 1:是")
    @Schema(title = "是否同意 0:否 1:是", description = "是否同意 0:否 1:是")
    private Integer consent;
}
