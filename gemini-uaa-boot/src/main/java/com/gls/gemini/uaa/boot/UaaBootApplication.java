package com.gls.gemini.uaa.boot;

import com.gls.gemini.uaa.boot.properties.UaaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Gemini UAA 启动类
 *
 * @author gemini 自动生成
 * @version 0.0.1-SNAPSHOT
 */
@SpringBootApplication
@EnableConfigurationProperties({UaaProperties.class})
public class UaaBootApplication {
    /**
     * Gemini UAA启动入口
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(UaaBootApplication.class, args);
    }
}