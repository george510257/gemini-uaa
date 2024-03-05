package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.UserOrganizationRelVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户组织关系表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "userOrganizationRel", path = "/userOrganizationRel")
public interface UserOrganizationRelFeign extends BaseFeign<UserOrganizationRelVo> {
}
