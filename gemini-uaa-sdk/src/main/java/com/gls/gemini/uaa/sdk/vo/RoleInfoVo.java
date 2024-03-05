package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "roleInfo", description = "角色信息表Vo")
public class RoleInfoVo extends BaseVo {

    /**
     * 角色编码
     */
    @ExcelProperty(value = "角色编码")
    @Schema(title = "角色编码", description = "角色编码")
    private String code;
    /**
     * 角色名称
     */
    @ExcelProperty(value = "角色名称")
    @Schema(title = "角色名称", description = "角色名称")
    private String name;
    /**
     * 角色描述
     */
    @ExcelProperty(value = "角色描述")
    @Schema(title = "角色描述", description = "角色描述")
    private String description;
    /**
     * 角色类型id 0:公共角色类型
     */
    @ExcelProperty(value = "角色类型id 0:公共角色类型")
    @Schema(title = "角色类型id 0:公共角色类型", description = "角色类型id 0:公共角色类型")
    private Long typeId;
    /**
     * 父角色id 0:无父角色
     */
    @ExcelProperty(value = "父角色id 0:无父角色")
    @Schema(title = "父角色id 0:无父角色", description = "父角色id 0:无父角色")
    private Long parentId;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    @Schema(title = "排序", description = "排序")
    private Integer sort;
}
