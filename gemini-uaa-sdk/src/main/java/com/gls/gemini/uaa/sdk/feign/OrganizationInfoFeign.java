package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.OrganizationInfoVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 组织信息表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "organizationInfo", path = "/organizationInfo")
public interface OrganizationInfoFeign extends BaseFeign<OrganizationInfoVo> {
}
