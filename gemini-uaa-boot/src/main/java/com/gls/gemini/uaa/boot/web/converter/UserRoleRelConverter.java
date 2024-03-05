package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.uaa.boot.web.entity.UserRoleRelEntity;
import com.gls.gemini.uaa.sdk.vo.UserRoleRelVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * 用户角色关系表 转换器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserRoleRelConverter extends BaseConverter<UserRoleRelVo, UserRoleRelEntity> {
}
