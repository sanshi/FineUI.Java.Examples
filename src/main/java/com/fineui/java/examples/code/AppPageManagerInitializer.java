package com.fineui.java.examples.code;

import com.fineui.java.core.BuildConstants;
import com.fineui.java.core.PageManager;
import com.fineui.java.web.FineUIPageManagerInitializer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 全局页面级配置初始化：每次首屏渲染前读 cookie，按当前用户偏好切换主题 / 语言 / 显示模式。
 *
 * <p>因在渲染前执行，这里设置的 theme/language 才来得及影响 head 里输出的主题 CSS / 语言包标签
 * （对页面无 FOUC）。用户切换偏好的典型做法：前端 {@code document.cookie} 写入对应 cookie 后整页刷新，
 * 下次请求本类即读到新值。作为 Spring {@code @Component}，它自动覆盖框架的空默认实现。
 *
 * <pre>
 *   Cookie             对应配置
 *   Theme              主题（如 pure_black；default 为内置默认，可不设）
 *   Language           语言（zh_CN / zh_TW / en）
 *   DisplayMode        显示模式（normal / compact / large）
 *   ShowOnlyCommunity  仅显示社区版示例（true 时禁用企业版特性：全局动画 / 移动自适应）
 * </pre>
 */
@Component
public class AppPageManagerInitializer implements FineUIPageManagerInitializer {

    @Override
    public void init(PageManager pm, HttpServletRequest request) {
        String theme = cookie(request, "Theme");
        if (theme != null && !theme.isEmpty()) {
            pm.theme(theme);
        }

        String language = cookie(request, "Language");
        if (language != null && !language.isEmpty()) {
            pm.language(language);
        }

        String displayMode = cookie(request, "DisplayMode");
        if (displayMode != null && !displayMode.isEmpty()) {
            pm.displayMode(displayMode);
        }

        // 1. 仅显示社区版示例，2. 社区版——两种情况都要禁用企业版特性（全局动画 / 移动自适应）
        boolean showOnlyCommunity = "true".equalsIgnoreCase(cookie(request, "ShowOnlyCommunity"));
        if (showOnlyCommunity || BuildConstants.IS_COMMUNITY_EDITION) {
            pm.enableAnimation(false);
            pm.mobileAdaption(false);
        }
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
