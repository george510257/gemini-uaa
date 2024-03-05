package com.gls.gemini.uaa.boot.web.converter;

import com.gls.gemini.common.core.base.BaseConverter;
import com.gls.gemini.uaa.boot.web.entity.UserOrganizationRelEntity;
import com.gls.gemini.uaa.sdk.vo.UserOrganizationRelVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * 用户组织关系表 转换器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserOrganizationRelConverter extends BaseConverter<UserOrganizationRelVo, UserOrganizationRelEntity> {
}
