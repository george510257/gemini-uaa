package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.OrganizationInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.OrganizationInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.OrganizationInfoMapper;
import com.gls.gemini.uaa.sdk.vo.OrganizationInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class OrganizationInfoService extends BaseServiceImpl<OrganizationInfoConverter, OrganizationInfoMapper, OrganizationInfoVo, OrganizationInfoEntity> {
    public List<OrganizationInfoEntity> loadOrganizationListByUserId(Long id) {
        return this.baseMapper.loadOrganizationListByUserId(id);
    }
}
