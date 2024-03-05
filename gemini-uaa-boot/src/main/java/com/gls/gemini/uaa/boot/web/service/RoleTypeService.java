package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.RoleTypeConverter;
import com.gls.gemini.uaa.boot.web.entity.RoleTypeEntity;
import com.gls.gemini.uaa.boot.web.mapper.RoleTypeMapper;
import com.gls.gemini.uaa.sdk.vo.RoleTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 角色类型表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class RoleTypeService extends BaseServiceImpl<RoleTypeConverter, RoleTypeMapper, RoleTypeVo, RoleTypeEntity> {
}
