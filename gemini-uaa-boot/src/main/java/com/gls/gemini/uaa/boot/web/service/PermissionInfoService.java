package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.PermissionInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.PermissionInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.PermissionInfoMapper;
import com.gls.gemini.uaa.sdk.vo.PermissionInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class PermissionInfoService extends BaseServiceImpl<PermissionInfoConverter, PermissionInfoMapper, PermissionInfoVo, PermissionInfoEntity> {
    public List<PermissionInfoEntity> loadPermissionListByUserId(Long id) {
        return this.baseMapper.loadPermissionListByUserId(id);
    }
}
