package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.UserOrganizationRelConverter;
import com.gls.gemini.uaa.boot.web.entity.UserOrganizationRelEntity;
import com.gls.gemini.uaa.boot.web.mapper.UserOrganizationRelMapper;
import com.gls.gemini.uaa.sdk.vo.UserOrganizationRelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户组织关系表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class UserOrganizationRelService extends BaseServiceImpl<UserOrganizationRelConverter, UserOrganizationRelMapper, UserOrganizationRelVo, UserOrganizationRelEntity> {
}
