package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织类型表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "organizationType", description = "组织类型表Vo")
public class OrganizationTypeVo extends BaseVo {

    /**
     * 组织类型编码
     */
    @ExcelProperty(value = "组织类型编码")
    @Schema(title = "组织类型编码", description = "组织类型编码")
    private String code;
    /**
     * 组织类型名称
     */
    @ExcelProperty(value = "组织类型名称")
    @Schema(title = "组织类型名称", description = "组织类型名称")
    private String name;
    /**
     * 组织类型描述
     */
    @ExcelProperty(value = "组织类型描述")
    @Schema(title = "组织类型描述", description = "组织类型描述")
    private String description;
}
