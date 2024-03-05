package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.RolePermissionRelConverter;
import com.gls.gemini.uaa.boot.web.entity.RolePermissionRelEntity;
import com.gls.gemini.uaa.boot.web.mapper.RolePermissionRelMapper;
import com.gls.gemini.uaa.sdk.vo.RolePermissionRelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 角色权限关系表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class RolePermissionRelService extends BaseServiceImpl<RolePermissionRelConverter, RolePermissionRelMapper, RolePermissionRelVo, RolePermissionRelEntity> {
}
