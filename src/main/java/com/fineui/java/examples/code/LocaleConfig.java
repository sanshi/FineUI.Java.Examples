package com.fineui.java.examples.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 多语言 Spring 配置：注册 {@link FineUILocaleResolver}，覆盖 Spring Boot 默认的
 * AcceptHeaderLocaleResolver，使服务端文本（MessageSource / {@code #{...}}）与 FineUI
 * 语言包共用 Cookie {@code Language} 单一来源。
 */
@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        return new FineUILocaleResolver();
    }
}
