package com.gls.gemini.uaa.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.web.jackson2.WebServletJackson2Module;

/**
 * UAA Jackson配置
 */
@Configuration
public class UaaJacksonConfig {

    /**
     * 注册CoreJackson2Module 对象
     *
     * @return CoreJackson2Module 对象
     */
    @Bean
    @Order(10)
    public CoreJackson2Module coreJackson2Module() {
        return new CoreJackson2Module();
    }

    /**
     * 注册OAuth2AuthorizationServerJackson2Module 对象
     *
     * @return OAuth2AuthorizationServerJackson2Module 对象
     */
    @Bean
    @Order(11)
    public OAuth2AuthorizationServerJackson2Module oauth2AuthorizationServerJackson2Module() {
        return new OAuth2AuthorizationServerJackson2Module();
    }

    /**
     * 注册WebServletJackson2Module 对象
     *
     * @return WebServletJackson2Module 对象
     */
    @Bean
    @Order(12)
    public WebServletJackson2Module webServletJackson2Module() {
        return new WebServletJackson2Module();
    }
}
