package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.UserRoleRelVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户角色关系表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "userRoleRel", path = "/userRoleRel")
public interface UserRoleRelFeign extends BaseFeign<UserRoleRelVo> {
}
