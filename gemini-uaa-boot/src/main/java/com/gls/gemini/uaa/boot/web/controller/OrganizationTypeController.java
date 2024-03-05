package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.boot.core.base.BaseController;
import com.gls.gemini.uaa.boot.web.service.OrganizationTypeService;
import com.gls.gemini.uaa.sdk.feign.OrganizationTypeFeign;
import com.gls.gemini.uaa.sdk.vo.OrganizationTypeVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织类型表 控制器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/organizationType")
@Tag(name = "organizationType", description = "组织类型表")
public class OrganizationTypeController extends BaseController<OrganizationTypeService, OrganizationTypeVo> implements OrganizationTypeFeign {
}