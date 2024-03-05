package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.OrganizationTypeConverter;
import com.gls.gemini.uaa.boot.web.entity.OrganizationTypeEntity;
import com.gls.gemini.uaa.boot.web.mapper.OrganizationTypeMapper;
import com.gls.gemini.uaa.sdk.vo.OrganizationTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 组织类型表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class OrganizationTypeService extends BaseServiceImpl<OrganizationTypeConverter, OrganizationTypeMapper, OrganizationTypeVo, OrganizationTypeEntity> {
}
