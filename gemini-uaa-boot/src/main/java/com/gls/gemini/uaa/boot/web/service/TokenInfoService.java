package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.TokenInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.TokenInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.TokenInfoMapper;
import com.gls.gemini.uaa.sdk.vo.TokenInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * token信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class TokenInfoService extends BaseServiceImpl<TokenInfoConverter, TokenInfoMapper, TokenInfoVo, TokenInfoEntity> {
}
