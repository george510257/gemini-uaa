package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.TenantTypeVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 租户类型表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "tenantType", path = "/tenantType")
public interface TenantTypeFeign extends BaseFeign<TenantTypeVo> {
}
