package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "organizationInfo", description = "组织信息表Vo")
public class OrganizationInfoVo extends BaseVo {

    /**
     * 组织编码
     */
    @ExcelProperty(value = "组织编码")
    @Schema(title = "组织编码", description = "组织编码")
    private String code;
    /**
     * 组织名称
     */
    @ExcelProperty(value = "组织名称")
    @Schema(title = "组织名称", description = "组织名称")
    private String name;
    /**
     * 组织描述
     */
    @ExcelProperty(value = "组织描述")
    @Schema(title = "组织描述", description = "组织描述")
    private String description;
    /**
     * 组织类型id 0:公共组织类型
     */
    @ExcelProperty(value = "组织类型id 0:公共组织类型")
    @Schema(title = "组织类型id 0:公共组织类型", description = "组织类型id 0:公共组织类型")
    private Long typeId;
    /**
     * 父组织id 0:无父组织
     */
    @ExcelProperty(value = "父组织id 0:无父组织")
    @Schema(title = "父组织id 0:无父组织", description = "父组织id 0:无父组织")
    private Long parentId;
    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    @Schema(title = "排序", description = "排序")
    private Integer sort;
}
