package com.gls.gemini.uaa.boot.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gls.gemini.uaa.boot.web.entity.PermissionInfoEntity;

import java.util.List;

/**
 * 权限信息表 Mapper
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
public interface PermissionInfoMapper extends BaseMapper<PermissionInfoEntity> {
    List<PermissionInfoEntity> loadPermissionListByUserId(Long id);
}
