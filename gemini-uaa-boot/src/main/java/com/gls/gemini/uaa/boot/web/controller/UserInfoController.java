package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.boot.core.base.BaseController;
import com.gls.gemini.uaa.boot.web.service.UserInfoService;
import com.gls.gemini.uaa.sdk.feign.UserInfoFeign;
import com.gls.gemini.uaa.sdk.vo.UserInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息表 控制器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/userInfo")
@Tag(name = "userInfo", description = "用户信息表")
public class UserInfoController extends BaseController<UserInfoService, UserInfoVo> implements UserInfoFeign {
}