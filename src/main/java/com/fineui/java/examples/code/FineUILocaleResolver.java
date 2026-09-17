package com.fineui.java.examples.code;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

/**
 * FineUI 多语言 {@link LocaleResolver}：从 Cookie {@code Language}（与 FineUI 语言包共用同一 cookie）
 * 解析 Spring Locale，驱动 MessageSource / Thymeleaf {@code #{...}} 的服务端文本。
 *
 * <p>映射：{@code zh_CN→Locale("zh","CN")}、{@code zh_TW→Locale("zh","TW")}、{@code en→Locale.ENGLISH}；
 * 自定义语言（如 zh_UEY）与缺失/非法值回落默认 zh-CN（自定义语言只有客户端语言包，服务端文化走默认）。
 *
 * <p><b>必须用 Java 老式 Locale 构造</b>（toString={@code zh_CN}）：Spring ResourceBundleMessageSource 按
 * {@code locale.toString()} 查找 {@code messages_zh_CN.properties}；若用 BCP47（{@code zh-CN}）会找
 * {@code messages_zh-CN} 而 miss。
 */
public class FineUILocaleResolver implements LocaleResolver {

    /** 默认语言：简体中文。 */
    public static final Locale DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE;

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String lang = cookie(request, "Language");
        if (lang == null || lang.isEmpty()) {
            return DEFAULT_LOCALE;
        }
        return switch (lang) {
            case "zh_CN" -> Locale.SIMPLIFIED_CHINESE;
            case "zh_TW" -> Locale.TRADITIONAL_CHINESE;
            case "en" -> Locale.ENGLISH;
            default -> DEFAULT_LOCALE;
        };
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // 语言切换由前端 setCookie('Language') + reload 完成；此处仅作完整性实现（写同名 cookie）。
        response.addCookie(new Cookie("Language", locale.toString()));
    }

    /** 读取指定 cookie 的值；无则返回 null。 */
    private static String cookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }
}
