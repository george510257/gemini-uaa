package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.AuthorizationInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.AuthorizationInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.AuthorizationInfoMapper;
import com.gls.gemini.uaa.sdk.vo.AuthorizationInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 授权信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class AuthorizationInfoService extends BaseServiceImpl<AuthorizationInfoConverter, AuthorizationInfoMapper, AuthorizationInfoVo, AuthorizationInfoEntity> {
}
