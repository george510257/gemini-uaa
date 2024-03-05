package com.gls.gemini.uaa.boot.web.service;

import com.gls.gemini.starter.mybatis.base.BaseServiceImpl;
import com.gls.gemini.uaa.boot.web.converter.ClientInfoConverter;
import com.gls.gemini.uaa.boot.web.entity.ClientInfoEntity;
import com.gls.gemini.uaa.boot.web.mapper.ClientInfoMapper;
import com.gls.gemini.uaa.sdk.vo.ClientInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 客户端信息表 服务实现类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@Service
public class ClientInfoService extends BaseServiceImpl<ClientInfoConverter, ClientInfoMapper, ClientInfoVo, ClientInfoEntity> {
}
