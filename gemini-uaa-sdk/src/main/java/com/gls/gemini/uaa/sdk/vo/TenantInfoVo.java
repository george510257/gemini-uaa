package com.gls.gemini.uaa.sdk.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gls.gemini.sdk.core.vo.BaseVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户信息表 Vo
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(title = "tenantInfo", description = "租户信息表Vo")
public class TenantInfoVo extends BaseVo {

    /**
     * 租户编码
     */
    @ExcelProperty(value = "租户编码")
    @Schema(title = "租户编码", description = "租户编码")
    private String code;
    /**
     * 租户名称
     */
    @ExcelProperty(value = "租户名称")
    @Schema(title = "租户名称", description = "租户名称")
    private String name;
    /**
     * 租户描述
     */
    @ExcelProperty(value = "租户描述")
    @Schema(title = "租户描述", description = "租户描述")
    private String description;
    /**
     * 租户类型id 0:公共租户类型
     */
    @ExcelProperty(value = "租户类型id 0:公共租户类型")
    @Schema(title = "租户类型id 0:公共租户类型", description = "租户类型id 0:公共租户类型")
    private Long typeId;
}
