package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.boot.core.base.BaseController;
import com.gls.gemini.uaa.boot.web.service.UserRoleRelService;
import com.gls.gemini.uaa.sdk.feign.UserRoleRelFeign;
import com.gls.gemini.uaa.sdk.vo.UserRoleRelVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色关系表 控制器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/userRoleRel")
@Tag(name = "userRoleRel", description = "用户角色关系表")
public class UserRoleRelController extends BaseController<UserRoleRelService, UserRoleRelVo> implements UserRoleRelFeign {
}