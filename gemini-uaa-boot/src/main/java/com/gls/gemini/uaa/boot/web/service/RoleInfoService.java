package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.RoleInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.RoleInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.RoleInfoMapper;
import com.gls.gemini.uaa.sdk.vo.RoleInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class RoleInfoService extends BaseServiceImpl<RoleInfoConverter, RoleInfoMapper, RoleInfoVo, RoleInfoEntity> {
    public List<RoleInfoEntity> loadRoleListByUserId(Long id) {
        return this.baseMapper.loadRoleListByUserId(id);
    }
}
