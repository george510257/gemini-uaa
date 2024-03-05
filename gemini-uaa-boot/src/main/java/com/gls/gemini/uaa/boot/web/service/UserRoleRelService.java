package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.UserRoleRelConverter;
import com.gls.gemini.uaa.boot.web.entity.UserRoleRelEntity;
import com.gls.gemini.uaa.boot.web.mapper.UserRoleRelMapper;
import com.gls.gemini.uaa.sdk.vo.UserRoleRelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户角色关系表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class UserRoleRelService extends BaseServiceImpl<UserRoleRelConverter, UserRoleRelMapper, UserRoleRelVo, UserRoleRelEntity> {
}
