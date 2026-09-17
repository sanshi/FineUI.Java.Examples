package com.fineui.java.examples.home;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * “查看源代码”里额外源文件声明的解析工具。
 *
 * <p>示例页在模板 head 里用 <code>meta[name=sourcefiles]</code>（分号分隔）声明本页引用的<b>其它</b>源文件，
 * 让它们也进“查看源代码”的选项卡。每一项都写成 <code>前缀:值</code>——前缀说明这是哪类东西，值是能直接
 * 定位到它的完整标识，不做任何省略，读声明即知去哪儿找：
 *
 * <ul>
 *   <li><b>{@code route:}</b> 另一个已注册示例页的路由，即它 {@code @FineUIPage} 注解里那个串（同时也是它的
 *       URL 路径）。产出<b>两个</b>选项卡：模板 {@code .html} 与页面类 {@code .java}。
 *       例：{@code route:grid/iframe-window}</li>
 *   <li><b>{@code template:}</b> 无路由的模板片段（局部视图），值是 {@code src/main/resources/templates/} 下的
 *       相对路径，含 {@code .html}。例：{@code template:partial/partial-view.html}</li>
 *   <li><b>{@code class:}</b> 示例工程里的 Java 类（数据服务 Controller / 数据模型 / 辅助类），值是<b>全限定
 *       类名</b>（IDE 里可直接跳转）。仅接受 {@code com.fineui.java.examples} 包下的类。
 *       例：{@code class:com.fineui.java.examples.web.AlertDownloadTextFileController}</li>
 *   <li><b>{@code res:}</b> 静态资源 / 数据文件（js、css、xml），值是 {@code src/main/resources/} 下的相对路径
 *       （web 可访问的在 {@code static/} 下）。例：{@code res:static/res/js/grid.js}、
 *       {@code res:data/tree/website.xml}</li>
 * </ul>
 *
 * <p>无前缀的项不是声明，而是首页传进来的“当前页 IFrame 地址”，由 {@link Source} 按 URL/路由处理。
 *
 * <p>安全：三类值都在这里做白名单校验（字符集受限，拒绝 {@code ..} 穿越、绝对路径、协议前缀、包外类），
 * 且必须真实存在才建选项卡——从根上杜绝任意路径读取。
 */
final class SourceFiles {

    /** {@code class:} 只接受这个包下的类（示例工程自身的代码）。 */
    private static final String BASE_PACKAGE = "com.fineui.java.examples.";

    private SourceFiles() {
    }

    /**
     * 校验 {@code template:} 的值：{@code templates/} 下的相对路径、含 {@code .html} 且文件确实存在，
     * 返回原值备读取用；不合法或不存在返回 {@code null}。
     */
    static String resolveTemplate(String value) {
        String path = safePath(value);
        if (path == null || !path.endsWith(".html")) {
            return null;
        }
        return exists("/templates/" + path) ? path : null;
    }

    /** 读模板源码（classpath {@code /templates/{相对路径}}），读不到返回 {@code null}。 */
    static String readTemplate(String templatePath) {
        return read("/templates/" + templatePath);
    }

    /**
     * 校验并加载 {@code class:} 的值（全限定类名）：必须在 {@link #BASE_PACKAGE} 下、只含合法标识符字符，
     * 且类确实存在；否则返回 {@code null}。
     */
    static Class<?> resolveClass(String value) {
        String name = value == null ? "" : value.trim();
        if (!name.startsWith(BASE_PACKAGE) || !name.matches("[A-Za-z0-9_.]+")
                || name.contains("..") || name.endsWith(".")) {
            return null;
        }
        try {
            return Class.forName(name);
        } catch (Throwable ignore) {
            return null;
        }
    }

    /**
     * 校验 {@code res:} 的值：{@code src/main/resources/} 下的相对路径、位于 {@link #RESOURCE_ROOTS}
     * 之一、且文件确实存在，返回原值备读取用；不合法或不存在返回 {@code null}。
     *
     * <p>限定根目录是为了不让"查看源代码"变成 classpath 任意资源的读取入口——否则
     * {@code application.properties}、授权文件、编译出的 {@code .class} 都会被读出来。示例要展示的
     * 资源只有静态资源（{@code static/}）与数据文件（{@code data/}）两类；将来要放开新根目录，
     * 在这里显式加一项。
     */
    static String resolveResource(String value) {
        String path = safePath(value);
        if (path == null || !underAllowedRoot(path)) {
            return null;
        }
        return exists("/" + path) ? path : null;
    }

    /** {@code res:} 允许读取的根目录：web 静态资源与 XML 等数据文件。 */
    private static final String[] RESOURCE_ROOTS = {"static/", "data/"};

    private static boolean underAllowedRoot(String path) {
        for (String root : RESOURCE_ROOTS) {
            if (path.startsWith(root)) {
                return true;
            }
        }
        return false;
    }

    /** 读静态资源 / 数据文件（classpath {@code /{相对路径}}），读不到返回 {@code null}。 */
    static String readResource(String resourcePath) {
        return read("/" + resourcePath);
    }

    /**
     * classpath 相对路径白名单校验：只允许字母数字与 {@code _ . / -}，拒绝空串、{@code ..} 穿越、
     * 前导斜杠（绝对路径）、反斜杠与冒号（协议前缀 / 盘符）；不合法返回 {@code null}。
     */
    private static String safePath(String value) {
        String path = value == null ? "" : value.trim();
        if (path.isEmpty() || path.startsWith("/") || path.contains("..")
                || !path.matches("[A-Za-z0-9_./-]+")) {
            return null;
        }
        return path;
    }

    /** classpath 上是否存在该资源。 */
    private static boolean exists(String classpathPath) {
        try (InputStream in = SourceFiles.class.getResourceAsStream(classpathPath)) {
            return in != null;
        } catch (Exception ignore) {
            return false;
        }
    }

    /** 按 UTF-8 读 classpath 资源，读不到返回 {@code null}。 */
    private static String read(String classpathPath) {
        try (InputStream in = SourceFiles.class.getResourceAsStream(classpathPath)) {
            return in == null ? null : new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception ignore) {
            return null;
        }
    }
}
