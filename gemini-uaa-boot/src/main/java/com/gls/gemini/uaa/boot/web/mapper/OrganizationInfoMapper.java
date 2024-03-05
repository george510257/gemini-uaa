package com.gls.gemini.uaa.boot.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gls.gemini.uaa.boot.web.entity.OrganizationInfoEntity;

import java.util.List;

/**
 * 组织信息表 Mapper
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
public interface OrganizationInfoMapper extends BaseMapper<OrganizationInfoEntity> {
    List<OrganizationInfoEntity> loadOrganizationListByUserId(Long id);
}
