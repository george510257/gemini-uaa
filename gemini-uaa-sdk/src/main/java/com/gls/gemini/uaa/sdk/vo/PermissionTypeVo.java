package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限类型表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "permissionType", description = "权限类型表Vo")
public class PermissionTypeVo extends BaseVo {

    /**
     * 权限类型编码
     */
    @ExcelProperty(value = "权限类型编码")
    @Schema(title = "权限类型编码", description = "权限类型编码")
    private String code;
    /**
     * 权限类型名称
     */
    @ExcelProperty(value = "权限类型名称")
    @Schema(title = "权限类型名称", description = "权限类型名称")
    private String name;
    /**
     * 权限类型描述
     */
    @ExcelProperty(value = "权限类型描述")
    @Schema(title = "权限类型描述", description = "权限类型描述")
    private String description;
}
