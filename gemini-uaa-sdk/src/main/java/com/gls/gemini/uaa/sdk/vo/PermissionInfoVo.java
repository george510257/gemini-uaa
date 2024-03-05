package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "permissionInfo", description = "权限信息表Vo")
public class PermissionInfoVo extends BaseVo {

    /**
     * 权限编码
     */
    @ExcelProperty(value = "权限编码")
    @Schema(title = "权限编码", description = "权限编码")
    private String code;
    /**
     * 权限名称
     */
    @ExcelProperty(value = "权限名称")
    @Schema(title = "权限名称", description = "权限名称")
    private String name;
    /**
     * 权限描述
     */
    @ExcelProperty(value = "权限描述")
    @Schema(title = "权限描述", description = "权限描述")
    private String description;
    /**
     * 权限类型id 0:公共权限类型
     */
    @ExcelProperty(value = "权限类型id 0:公共权限类型")
    @Schema(title = "权限类型id 0:公共权限类型", description = "权限类型id 0:公共权限类型")
    private Long typeId;
    /**
     * 父权限id 0:无父权限
     */
    @ExcelProperty(value = "父权限id 0:无父权限")
    @Schema(title = "父权限id 0:无父权限", description = "父权限id 0:无父权限")
    private Long parentId;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    @Schema(title = "排序", description = "排序")
    private Integer sort;
    /**
     * 命令
     */
    @ExcelProperty(value = "命令")
    @Schema(title = "命令", description = "命令")
    private String command;
}
