package com.gls.gemini.uaa.boot.authentication.handler;

import com.gls.gemini.common.core.domain.Result;
import com.gls.gemini.common.core.enums.ResultEnums;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 默认认证失败处理器
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Resource
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        // 设置状态码
        httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        // 获取异常信息
        String message = exception.getLocalizedMessage();
        if (exception instanceof OAuth2AuthenticationException oauth2AuthenticationException) {
            message = oauth2AuthenticationException.getError().toString();
        }
        Result<String> result = ResultEnums.UNAUTHORIZED.getResult(message);
        // 写出错误信息
        mappingJackson2HttpMessageConverter.write(result, MediaType.APPLICATION_JSON, httpResponse);
    }
}
