package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限关系表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "rolePermissionRel", description = "角色权限关系表Vo")
public class RolePermissionRelVo extends BaseVo {

    /**
     * 角色id
     */
    @ExcelProperty(value = "角色id")
    @Schema(title = "角色id", description = "角色id")
    private Long roleId;
    /**
     * 权限id
     */
    @ExcelProperty(value = "权限id")
    @Schema(title = "权限id", description = "权限id")
    private Long permissionId;
}
