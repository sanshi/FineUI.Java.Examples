package com.fineui.java.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FineUI.Java Demo 启动类。
 *
 * <p>演示"标签式有状态服务端组件"范式在 Java / Spring Boot 上的端到端最小闭环：
 * {@code <f:button>} / {@code <f:grid>} 标签 → Thymeleaf 方言 → F.create 输出
 * → 点击 → AJAX 回发 → F_STATE 重建控件树 → 事件处理器 → 增量回传 → 局部更新。
 *
 * <p>本类位于 {@code com.fineui.java.examples}：组件扫描只覆盖 examples 自身，fineui-java 库的方言 / 过滤器 /
 * 回发端点全部经<b>自动配置</b>装配（非组件扫描），故业务侧零 {@code @Configuration}。
 * {@code @FineUIPage} 页面也在此包下，由 starter 的扫描器自动登记路由。
 */
@SpringBootApplication
public class FineUIJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FineUIJavaApplication.class, args);
    }
}
