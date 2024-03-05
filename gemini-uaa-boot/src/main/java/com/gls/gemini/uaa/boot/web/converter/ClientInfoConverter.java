package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.uaa.boot.web.entity.ClientInfoEntity;
import com.gls.gemini.uaa.sdk.vo.ClientInfoVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * 客户端信息表 转换器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientInfoConverter extends BaseConverter<ClientInfoVo, ClientInfoEntity> {
}
