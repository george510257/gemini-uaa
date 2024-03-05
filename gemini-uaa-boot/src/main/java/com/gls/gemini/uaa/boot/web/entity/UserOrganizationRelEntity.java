package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户组织关系表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_organization_rel")
public class UserOrganizationRelEntity extends BaseEntity {

    public static final String COL_USER_ID = "user_id";
    public static final String COL_ORGANIZATION_ID = "organization_id";
    public static final String COL_DEFAULT_FLAG = "default_flag";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 组织id
     */
    private Long organizationId;
    /**
     * 是否默认 0:否 1:是
     */
    private Integer defaultFlag;
}
