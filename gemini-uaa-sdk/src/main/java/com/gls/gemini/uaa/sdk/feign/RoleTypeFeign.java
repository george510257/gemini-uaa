package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.RoleTypeVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 角色类型表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "roleType", path = "/roleType")
public interface RoleTypeFeign extends BaseFeign<RoleTypeVo> {
}
