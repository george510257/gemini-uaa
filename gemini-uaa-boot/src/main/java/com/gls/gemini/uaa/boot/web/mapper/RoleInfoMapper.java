package com.gls.gemini.uaa.boot.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gls.gemini.uaa.boot.web.entity.RoleInfoEntity;

import java.util.List;

/**
 * 角色信息表 Mapper
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfoEntity> {
    List<RoleInfoEntity> loadRoleListByUserId(Long id);
}
