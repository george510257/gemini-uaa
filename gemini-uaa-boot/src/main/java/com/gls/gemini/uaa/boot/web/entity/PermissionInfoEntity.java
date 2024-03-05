package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 权限信息表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_permission_info")
public class PermissionInfoEntity extends BaseEntity {

    public static final String COL_CODE = "code";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TYPE_ID = "type_id";
    public static final String COL_PARENT_ID = "parent_id";
    public static final String COL_SORT = "sort";
    public static final String COL_COMMAND = "command";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限描述
     */
    private String description;
    /**
     * 权限类型id 0:公共权限类型
     */
    private Long typeId;
    /**
     * 父权限id 0:无父权限
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 命令
     */
    private String command;
}
