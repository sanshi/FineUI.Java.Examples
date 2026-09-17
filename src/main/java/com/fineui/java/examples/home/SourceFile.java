package com.fineui.java.examples.home;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 查看源代码-单文件页（路由 {@code home/source-file}）：由 {@code source} 页的选项卡以 IFrame 加载，
 * {@code ?kind=template|class|res&path=<定位串>} 指定要展示的源文件，HTML 转义后交 google-code-prettify
 * 高亮；prettify 皮肤随当前主题（{@code Theme} cookie）在浅色 / 深色间切换。
 *
 * <p>{@code kind} 与示例页 {@code meta[name=sourcefiles]} 的声明前缀同名（见 {@link SourceFiles}），
 * {@code path} 是对应的定位串：
 *
 * <ul>
 *   <li>{@code kind=template}：{@code path} 是 {@code templates/} 下的相对路径（含 {@code .html}），
 *       从 classpath {@code /templates/} 读取；</li>
 *   <li>{@code kind=class}：{@code path} 是全限定类名，部署态从 classpath {@code /source/java/}
 *       （打包脚本注入）读取，开发态从文件系统 src 读取；</li>
 *   <li>{@code kind=res}：{@code path} 是 {@code src/main/resources/} 下的相对路径，从 classpath 根读取。</li>
 * </ul>
 *
 * <p>本页不含 FineUI 控件——高亮结果直接由模板 {@code th:utext="${page.sourceHtml}"} 输出到 body。
 *
 * <p>安全：{@code path} 一律先经 {@link SourceFiles} 白名单校验（拒绝 {@code ..} 穿越 / 绝对路径 /
 * {@code com.fineui.java.examples} 包外的类）且确认文件存在，才会真正读取——从根上杜绝任意路径读取。
 */
@FineUIPage("home/source-file")
public class SourceFile extends PageBase {

    /** 单文件高亮 HTML（模板 {@code th:utext} 读取）：据 {@code ?kind}/{@code ?path} 现取现算。 */
    public String getSourceHtml() {
        return buildHtml(getQueryParam("kind"), getQueryParam("path"));
    }

    /**
     * prettify 皮肤 CSS 地址（模板 head 里 {@code <link>} 读取，须放自定义 {@code <style>} 之前，
     * 让自定义样式覆盖皮肤默认样式）：随当前主题在浅色 / 深色皮肤间切换。
     */
    public String getPrettifySkinHref() {
        String skin = "dark_hive".equals(themeName()) ? "tomorrow-night" : "tomorrow";
        HttpServletRequest req = currentRequest();
        String contextPath = req == null ? "" : req.getContextPath();
        String baseUrl = (contextPath == null || contextPath.isEmpty() || "/".equals(contextPath)) ? "" : contextPath;
        return baseUrl + "/res/google-code-prettify/themes/" + skin + ".min.css";
    }

    /** 据 kind+path 读取单个文件、高亮成 {@code <pre>}（皮肤 link 由 head 输出，见 {@link #getPrettifySkinHref()}）。 */
    private String buildHtml(String kind, String path) {
        String content;
        if ("template".equals(kind)) {
            String templatePath = SourceFiles.resolveTemplate(path);
            content = templatePath == null ? null : SourceFiles.readTemplate(templatePath);
        } else if ("class".equals(kind)) {
            Class<?> clazz = SourceFiles.resolveClass(path);
            content = clazz == null ? null : readJavaSrc(clazz);
        } else if ("res".equals(kind)) {
            String resourcePath = SourceFiles.resolveResource(path);
            content = resourcePath == null ? null : SourceFiles.readResource(resourcePath);
        } else {
            return error();
        }
        if (content == null) {
            return error();
        }
        return "<pre class=\"prettyprint linenums\">" + escape(content) + "</pre>";
    }

    /**
     * 读某个示例 Java 类的源：部署态优先从 classpath 读打包脚本注入的源码副本（{@code /source/java/{包/类}.java}，
     * 与 {@code templates/} 同机制，服务器上没有文件系统 src）；开发态 classpath 无此资源，回落文件系统按
     * {@code {包/类}.java} 相对路径定位。
     */
    private String readJavaSrc(Class<?> clazz) {
        String className = clazz.getName().replace('.', '/');
        try (InputStream in = getClass().getResourceAsStream("/source/java/" + className + ".java")) {
            if (in != null) {
                return new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (Exception ignore) {
            // classpath 读取失败：回落文件系统
        }
        String rel = "fineui-java-examples/src/main/java/" + className + ".java";
        for (String base : new String[]{"", "FineUI.Java/", "../"}) {
            Path p = Path.of(base + rel);
            if (Files.isReadable(p)) {
                try {
                    return Files.readString(p, StandardCharsets.UTF_8);
                } catch (Exception ignore) {
                    return null;   // 读失败：源码查看是辅助功能，静默降级
                }
            }
        }
        return null;
    }

    private static String error() {
        return "<div style=\"padding:24px;color:#888;\">未找到该源文件。</div>";
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    /** 当前主题名（{@code Theme} cookie，小写；缺省 pure_black）。 */
    private static String themeName() {
        String v = cookie("Theme");
        return (v == null || v.isEmpty()) ? "pure_black" : v.toLowerCase();
    }

    private static String cookie(String name) {
        HttpServletRequest req = currentRequest();
        if (req == null || req.getCookies() == null) {
            return null;
        }
        for (Cookie c : req.getCookies()) {
            if (name.equals(c.getName())) {
                return c.getValue();
            }
        }
        return null;
    }

    private static HttpServletRequest currentRequest() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        return (attrs instanceof ServletRequestAttributes sra) ? sra.getRequest() : null;
    }
}
