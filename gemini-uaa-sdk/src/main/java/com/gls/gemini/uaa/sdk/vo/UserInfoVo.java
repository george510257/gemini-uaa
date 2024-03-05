package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "userInfo", description = "用户信息表Vo")
public class UserInfoVo extends BaseVo {

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    @Schema(title = "用户名", description = "用户名")
    private String username;
    /**
     * 密码
     */
    @ExcelProperty(value = "密码")
    @Schema(title = "密码", description = "密码")
    private String password;
    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    @Schema(title = "手机号", description = "手机号")
    private String mobile;
    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    @Schema(title = "邮箱", description = "邮箱")
    private String email;
    /**
     * 真实姓名
     */
    @ExcelProperty(value = "真实姓名")
    @Schema(title = "真实姓名", description = "真实姓名")
    private String realName;
    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    @Schema(title = "昵称", description = "昵称")
    private String nickName;
    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    @Schema(title = "头像", description = "头像")
    private String avatar;
    /**
     * 语言
     */
    @ExcelProperty(value = "语言")
    @Schema(title = "语言", description = "语言")
    private String language;
    /**
     * 区域
     */
    @ExcelProperty(value = "区域")
    @Schema(title = "区域", description = "区域")
    private String locale;
    /**
     * 时区
     */
    @ExcelProperty(value = "时区")
    @Schema(title = "时区", description = "时区")
    private String timeZone;
}
