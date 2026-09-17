package com.fineui.java.examples.mobile;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 让预置菜单里的 {@code /mobile/?file=...} 链接可用的小重定向。
 *
 * <p>预置菜单叶子的地址形如 {@code /mobile/?file=button/button}，即路径 {@code /mobile/}（末尾带斜杠）
 * 加查询串。通用页面路由（每段字符集不含点、且默认不匹配末尾斜杠）不会把 {@code /mobile} 或 {@code /mobile/}
 * 命中到 {@code mobile/index}，故在此把这两条路径连同原查询串一起重定向到 {@code /mobile/index}，
 * 由预览器壳页在浏览器端读 {@code ?file=} 完成后续。
 *
 * <p>这是本移动示例唯一新增的 Spring 控制器：只做地址重定向、不含业务逻辑；{@code /mobile} 字面量映射
 * 比通用的 {@code /{段}} 模式更具体，会优先命中本控制器。
 */
@Controller
public class MobileRedirectController {

    @GetMapping({"/mobile", "/mobile/"})
    public String redirectToViewer(HttpServletRequest request) {
        String query = request.getQueryString();
        return "redirect:/mobile/index" + (query != null && !query.isEmpty() ? "?" + query : "");
    }
}
