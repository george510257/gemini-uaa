package com.gls.gemini.uaa.boot.web.controller;

import com.gls.gemini.common.core.domain.Result;
import com.gls.gemini.common.core.enums.ResultEnums;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @PostMapping("/hello")
    public Result<String> hello(Authentication authentication) {
        String message = String.format("Hello, %s ！！！", authentication.getName());
        return ResultEnums.SUCCESS.getResult(message);
    }
}
