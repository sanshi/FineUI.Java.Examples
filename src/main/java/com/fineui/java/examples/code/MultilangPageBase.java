package com.fineui.java.examples.code;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 多语言页面基类：基于 Spring MessageSource 提供服务端多语言文本，并支持把客户端 JS 资源
 * （{@code F.setResources}）按当前 Locale 翻译后交给模板输出。
 *
 * <p>用法：
 * <ul>
 *   <li>Java 侧：{@code _R("multilang.login.success")}、{@code _R("multilang.login.errorFormat", user, pwd)}（{@code {0}} 占位符）。</li>
 *   <li>模板侧：f: 控件属性用 Thymeleaf 消息表达式 {@code title="#{multilang.grid.title}"}；普通 HTML 用 {@code th:text="#{...}"}。</li>
 *   <li>JS 资源：{@code Page_Load} 里 {@code setJavaScriptResources("Male", "Female", ...)}，模板 script 段输出
 *       {@code F.setResources({...})}（见 {@link #getJavaScriptResources()}）。</li>
 * </ul>
 */
public abstract class MultilangPageBase extends PageBase {

    private final Map<String, String> jsResources = new LinkedHashMap<>();

    /** 服务端多语言文本源，由 Spring 构造器注入（页面模型是 prototype Bean，子类构造器 super(messageSource) 传入）。 */
    private final MessageSource messageSource;

    protected MultilangPageBase(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /** 取多语言资源；缺 key 回落 name 本身（对齐客户端 {@code F.getResource}）；支持 {@code {0}} 占位符。 */
    protected String _R(String name, Object... args) {
        return getResource(name, args);
    }

    /**
     * 模板取文本入口（public 供 {@code ${page.resource('key')}} 调用；SpEL 方法调用要求方法名精确匹配，
     * 不会把 {@code resource(...)} 自动映射到 {@code getResource(...)}）。
     */
    public String resource(String name, Object... args) {
        return getResource(name, args);
    }

    /** 单参便捷重载（消除 SpEL varargs 方法解析的依赖，模板 `${page.resource('key')}` 直接命中）。 */
    public String resource(String name) {
        return getResource(name);
    }

    /** 取多语言资源（同 {@link #_R}）；public 供模板/Java 侧统一调用。 */
    public String getResource(String name, Object... args) {
        if (messageSource == null) {
            return name;
        }
        try {
            // 四参重载：缺 key 回落 name（NoSuchMessageException 兜底）
            return messageSource.getMessage(name, args, name, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            return name;
        }
    }

    /** 声明注入的客户端 JS 资源（key 为客户端原名，如 {@code "Male"}）；服务端按当前 Locale 翻译。 */
    protected void setJavaScriptResources(String... clientKeys) {
        for (String key : clientKeys) {
            jsResources.put(key, getResource(key));
        }
    }

    /**
     * 模板输出用：已翻译的客户端 JS 资源（key→当前语言值）。Thymeleaf JS 内联（{@code th:inline="javascript"}）
     * 对 Map 直接序列化为 JSON 对象（不能返回 String——CharSequence 会被输出为 JS 字符串字面量导致
     * {@code F.setResources("...")} 注入失效），配合模板 {@code F.setResources(/*[[${page.javaScriptResources}]]*&#47; {});}。
     */
    public Map<String, String> getJavaScriptResources() {
        return jsResources;
    }
}
