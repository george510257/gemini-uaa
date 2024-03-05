package com.gls.gemini.uaa.sdk.feign;

import com.gls.gemini.sdk.core.feign.BaseFeign;
import com.gls.gemini.uaa.sdk.vo.UserInfoVo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户信息表 Feign
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@FeignClient(name = "gemini-uaa", contextId = "userInfo", path = "/userInfo")
public interface UserInfoFeign extends BaseFeign<UserInfoVo> {
}
