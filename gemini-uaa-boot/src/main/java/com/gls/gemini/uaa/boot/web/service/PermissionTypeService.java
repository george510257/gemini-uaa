package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.PermissionTypeConverter;
import com.gls.gemini.uaa.boot.web.entity.PermissionTypeEntity;
import com.gls.gemini.uaa.boot.web.mapper.PermissionTypeMapper;
import com.gls.gemini.uaa.sdk.vo.PermissionTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 权限类型表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class PermissionTypeService extends BaseServiceImpl<PermissionTypeConverter, PermissionTypeMapper, PermissionTypeVo, PermissionTypeEntity> {
}
