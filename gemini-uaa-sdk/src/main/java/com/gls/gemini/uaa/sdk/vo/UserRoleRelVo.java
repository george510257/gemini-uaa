package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关系表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "userRoleRel", description = "用户角色关系表Vo")
public class UserRoleRelVo extends BaseVo {

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    @Schema(title = "用户id", description = "用户id")
    private Long userId;
    /**
     * 角色id
     */
    @ExcelProperty(value = "角色id")
    @Schema(title = "角色id", description = "角色id")
    private Long roleId;
    /**
     * 是否默认 0:否 1:是
     */
    @ExcelProperty(value = "是否默认 0:否 1:是")
    @Schema(title = "是否默认 0:否 1:是", description = "是否默认 0:否 1:是")
    private Integer defaultFlag;
}
