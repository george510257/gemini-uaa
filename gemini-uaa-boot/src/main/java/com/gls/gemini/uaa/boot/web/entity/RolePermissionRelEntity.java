package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 角色权限关系表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role_permission_rel")
public class RolePermissionRelEntity extends BaseEntity {

    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_PERMISSION_ID = "permission_id";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 权限id
     */
    private Long permissionId;
}
