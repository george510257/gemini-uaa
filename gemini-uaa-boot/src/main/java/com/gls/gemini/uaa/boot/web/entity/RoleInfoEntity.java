package com.gls.gemini.uaa.boot.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gls.gemini.starter.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 角色信息表 实体类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role_info")
public class RoleInfoEntity extends BaseEntity {

    public static final String COL_CODE = "code";
    public static final String COL_NAME = "name";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_TYPE_ID = "type_id";
    public static final String COL_PARENT_ID = "parent_id";
    public static final String COL_SORT = "sort";
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 角色类型id 0:公共角色类型
     */
    private Long typeId;
    /**
     * 父角色id 0:无父角色
     */
    private Long parentId;
    /**
     * 排序
     */
    private Integer sort;
}
