package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 租户信息表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_tenant_info")
public class TenantInfoEntity extends BaseEntity {

    public static final String COL_CODE = "code";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TYPE_ID = "type_id";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 租户编码
     */
    private String code;
    /**
     * 租户名称
     */
    private String name;
    /**
     * 租户描述
     */
    private String description;
    /**
     * 租户类型id 0:公共租户类型
     */
    private Long typeId;
}
