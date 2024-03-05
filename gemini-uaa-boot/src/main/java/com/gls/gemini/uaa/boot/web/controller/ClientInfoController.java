package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.boot.core.base.BaseController;
import com.gls.gemini.uaa.boot.web.service.ClientInfoService;
import com.gls.gemini.uaa.sdk.feign.ClientInfoFeign;
import com.gls.gemini.uaa.sdk.vo.ClientInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端信息表 控制器
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@Slf4j
@RestController
@RequestMapping("/clientInfo")
@Tag(name = "clientInfo", description = "客户端信息表")
public class ClientInfoController extends BaseController<ClientInfoService, ClientInfoVo> implements ClientInfoFeign {
}