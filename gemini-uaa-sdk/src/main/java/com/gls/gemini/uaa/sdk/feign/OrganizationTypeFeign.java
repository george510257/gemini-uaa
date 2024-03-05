package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.OrganizationTypeVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 组织类型表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "organizationType", path = "/organizationType")
public interface OrganizationTypeFeign extends BaseFeign<OrganizationTypeVo> {
}
