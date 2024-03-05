package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户类型表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "tenantType", description = "租户类型表Vo")
public class TenantTypeVo extends BaseVo {

    /**
     * 租户类型编码
     */
    @ExcelProperty(value = "租户类型编码")
    @Schema(title = "租户类型编码", description = "租户类型编码")
    private String code;
    /**
     * 租户类型名称
     */
    @ExcelProperty(value = "租户类型名称")
    @Schema(title = "租户类型名称", description = "租户类型名称")
    private String name;
    /**
     * 租户类型描述
     */
    @ExcelProperty(value = "租户类型描述")
    @Schema(title = "租户类型描述", description = "租户类型描述")
    private String description;
}
