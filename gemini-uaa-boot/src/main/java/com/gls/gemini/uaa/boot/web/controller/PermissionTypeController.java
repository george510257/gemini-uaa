package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.boot.core.base.BaseController;
import com.gls.gemini.uaa.boot.web.service.PermissionTypeService;
import com.gls.gemini.uaa.sdk.feign.PermissionTypeFeign;
import com.gls.gemini.uaa.sdk.vo.PermissionTypeVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限类型表 控制器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/permissionType")
@Tag(name = "permissionType", description = "权限类型表")
public class PermissionTypeController extends BaseController<PermissionTypeService, PermissionTypeVo> implements PermissionTypeFeign {
}