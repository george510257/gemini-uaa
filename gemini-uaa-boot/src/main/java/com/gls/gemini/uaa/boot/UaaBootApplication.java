package com.gls.gemini.uaa.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * uaa 启动类
 */
@SpringBootApplication
public class UaaBootApplication {

    /**
     * uaa 启动入口
     *
     * @param args 启动参数
     */
    public static void main(String[] args) {
        SpringApplication.run(UaaBootApplication.class, args);
    }
}
