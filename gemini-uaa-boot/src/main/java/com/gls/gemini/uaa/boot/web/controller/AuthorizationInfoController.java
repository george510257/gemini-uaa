package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.boot.core.base.BaseController;
import com.gls.gemini.uaa.boot.web.service.AuthorizationInfoService;
import com.gls.gemini.uaa.sdk.feign.AuthorizationInfoFeign;
import com.gls.gemini.uaa.sdk.vo.AuthorizationInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权信息表 控制器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/authorizationInfo")
@Tag(name = "authorizationInfo", description = "授权信息表")
public class AuthorizationInfoController extends BaseController<AuthorizationInfoService, AuthorizationInfoVo> implements AuthorizationInfoFeign {
}