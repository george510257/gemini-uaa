package com.gls.gemini.uaa.boot.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.UserInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.OrganizationInfoEntity;
import com.gls.gemini.uaa.boot.web.entity.PermissionInfoEntity;
import com.gls.gemini.uaa.boot.web.entity.RoleInfoEntity;
import com.gls.gemini.uaa.boot.web.entity.UserInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.UserInfoMapper;
import com.gls.gemini.uaa.sdk.vo.UserInfoVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class UserInfoService extends BaseServiceImpl<UserInfoConverter, UserInfoMapper, UserInfoVo, UserInfoEntity> implements UserDetailsService {
    @Resource
    private RoleInfoService roleInfoService;
    @Resource
    private PermissionInfoService permissionInfoService;
    @Resource
    private OrganizationInfoService organizationInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: {}", username);
        // 查询用户信息
        UserInfoEntity userInfo = this.getOneOpt(new QueryWrapper<UserInfoEntity>().eq(UserInfoEntity.COL_USERNAME, username)).orElseThrow(() -> new UsernameNotFoundException("用户不存在"));
        // 查询用户角色
        List<RoleInfoEntity> roleInfoList = roleInfoService.loadRoleListByUserId(userInfo.getId());
        // 查询用户权限
        List<PermissionInfoEntity> permissionInfoList = permissionInfoService.loadPermissionListByUserId(userInfo.getId());
        // 查询用户组织
        List<OrganizationInfoEntity> organizationInfoList = organizationInfoService.loadOrganizationListByUserId(userInfo.getId());
        return null;
    }
}
