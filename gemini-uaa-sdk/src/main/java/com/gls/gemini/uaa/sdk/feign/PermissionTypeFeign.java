package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.PermissionTypeVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 权限类型表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "permissionType", path = "/permissionType")
public interface PermissionTypeFeign extends BaseFeign<PermissionTypeVo> {
}
