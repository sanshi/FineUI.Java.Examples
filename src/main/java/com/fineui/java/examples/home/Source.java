package com.fineui.java.examples.home;

import com.fineui.java.core.ControlRegistry;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.PageContext;
import com.fineui.java.core.PageRegistry;
import com.fineui.java.core.controls.Tab;
import com.fineui.java.core.controls.TabStrip;
import com.fineui.java.examples.code.PageBase;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 查看源代码页（路由 {@code home/source}）：首页“查看源代码”在弹窗里以 IFrame 加载本页，{@code ?files=}
 * 传入分号分隔的一串源文件，本页为每个源文件建一个选项卡，选项卡内再以 IFrame 承载 {@code source-file}
 * 子页做单文件语法高亮。
 *
 * <p>{@code files} 的第一项由首页脚本填入，是<b>当前示例页的 IFrame 地址</b>（无前缀）；其余各项来自该示例页
 * 模板里的 {@code meta[name=sourcefiles]}，一律写成 {@code 前缀:值} 的声明式形式（四种前缀见
 * {@link SourceFiles}）。一项产出几个选项卡：
 *
 * <ul>
 *   <li>当前页地址、{@code route:} → <b>两个</b>（模板 {@code .html} + 页面类 {@code .java}）；</li>
 *   <li>{@code template:}、{@code class:}、{@code res:} → <b>一个</b>。</li>
 * </ul>
 */
@FineUIPage("home/source")
public class Source extends PageBase {

    TabStrip TabStrip1;   // 绑定到 <f:tabstrip id="TabStrip1">

    /** 路由注册表，构造器注入（把当前页地址 / {@code route:} 声明解析成规范路由与页面类）。 */
    private final PageRegistry registry;

    public Source(PageRegistry registry) {
        this.registry = registry;
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            buildTabs(getQueryParam("files"));
        }
    }

    /** 为 {@code files}（分号分隔）里每一项建选项卡：按前缀分派，无前缀的按“当前页 IFrame 地址”处理。 */
    private void buildTabs(String files) {
        if (files == null || files.isBlank() || TabStrip1 == null) {
            return;
        }
        PageContext pc = PageContext.current();
        if (pc == null) {
            return;
        }
        for (String raw : files.split(";")) {
            String item = raw == null ? "" : raw.trim();
            if (item.isEmpty()) {
                continue;
            }
            int colon = item.indexOf(':');
            String prefix = colon > 0 ? item.substring(0, colon) : "";
            String value = colon > 0 ? item.substring(colon + 1) : "";
            switch (prefix) {
                case "route" -> addRouteTabs(pc, routeOf(value));
                case "template" -> addTemplateTab(pc, value);
                case "class" -> addClassTab(pc, value);
                case "res" -> addResourceTab(pc, value);
                // 其余一律按“当前页 IFrame 地址”处理：无前缀的第一项，以及 http(s):// 开头的外站地址
                // （外站地址解析不出注册路由，自然建不出选项卡）。前缀写错的声明同样落到这里、建不出选项卡。
                default -> addCurrentPageTabs(pc, item);
            }
        }
    }

    /**
     * 当前示例页的 IFrame 地址 → 该页的两个选项卡。
     *
     * <p>移动示例特判：预览器 URL 形如 {@code /mobile/?file=panel/panel}（或重定向后的
     * {@code /mobile/index?file=...}），用户想看的是 {@code file} 指向的移动页面源码，而非预览器壳页自身——
     * 命中注册路由则换成移动页路由，未命中按原地址处理。
     */
    private void addCurrentPageTabs(PageContext pc, String rawUrl) {
        String mobileRoute = resolveMobileRoute(rawUrl);
        addRouteTabs(pc, mobileRoute != null ? mobileRoute : stripContextPath(routeOf(rawUrl)));
    }

    /**
     * 已注册示例页 → 模板 + 页面类两个选项卡。路由先经注册表解析成登记时的规范路由（未注册返回 null、
     * 不建选项卡）——既保证模板路径与页面类都来自可信的注册表，也从根上挡掉 {@code ?files=../x} 之类的
     * 目录穿越（穿越串不会命中注册路由）。
     */
    private void addRouteTabs(PageContext pc, String route) {
        String canonical = (route == null || route.isEmpty()) ? null : registry.canonicalRoute(route);
        if (canonical == null) {
            return;
        }
        // 模板选项卡（.html）——路由 == 视图名 == 模板路径，故模板就在 templates/{规范路由}.html
        String templatePath = canonical + ".html";
        addTab(pc, fileName(templatePath), "templates/" + templatePath,
                sourceFileUrl("template", templatePath), iconUrl("html"));
        // 页面类选项卡（.java）——读注册表元数据取类，不实例化页面
        addClassTab(pc, registry.pageClass(canonical));
    }

    /** {@code template:} 声明 → 一个模板片段选项卡（校验不过 / 文件不存在则不建）。 */
    private void addTemplateTab(PageContext pc, String value) {
        String templatePath = SourceFiles.resolveTemplate(value);
        if (templatePath != null) {
            addTab(pc, fileName(templatePath), "templates/" + templatePath,
                    sourceFileUrl("template", templatePath), iconUrl("html"));
        }
    }

    /** {@code class:} 声明 → 一个 Java 源码选项卡（全限定类名解析不出类则不建）。 */
    private void addClassTab(PageContext pc, String value) {
        addClassTab(pc, SourceFiles.resolveClass(value));
    }

    /** 为一个已解析出的类建 Java 源码选项卡（悬浮提示给出 {@code src/main/java/} 下的相对路径）。 */
    private void addClassTab(PageContext pc, Class<?> clazz) {
        if (clazz != null) {
            addTab(pc, clazz.getSimpleName() + ".java", clazz.getName().replace('.', '/') + ".java",
                    sourceFileUrl("class", clazz.getName()), iconUrl("java"));
        }
    }

    /** {@code res:} 声明 → 一个静态资源 / 数据文件选项卡（校验不过 / 文件不存在则不建）。 */
    private void addResourceTab(PageContext pc, String value) {
        String resourcePath = SourceFiles.resolveResource(value);
        if (resourcePath == null) {
            return;
        }
        String name = fileName(resourcePath);
        String ext = name.contains(".") ? name.substring(name.lastIndexOf('.') + 1) : "txt";
        addTab(pc, name, resourcePath, sourceFileUrl("res", resourcePath), iconUrl(ext));
    }

    /** 单文件高亮子页的地址：{@code kind} 与声明前缀同名，{@code path} 是已校验的定位串。 */
    private static String sourceFileUrl(String kind, String path) {
        return "/home/source-file?kind=" + kind + "&path=" + path;
    }

    /** 取路径最后一段作为选项卡标题。 */
    private static String fileName(String path) {
        int slash = path.lastIndexOf('/');
        return slash >= 0 ? path.substring(slash + 1) : path;
    }

    /** 建一个 IFrame 选项卡并挂到 TabStrip1（标题 + 悬浮提示（完整源文件路径）+ IFrame 地址 + 文件类型图标）。 */
    private void addTab(PageContext pc, String title, String tooltip, String iframeUrl, String iconUrl) {
        Tab tab = (Tab) ControlRegistry.create("Tab");
        tab.setId(pc.assignChildId(TabStrip1, null));   // 无显式 id 时自动生成
        tab.putRaw("title", title);
        tab.setTitleToolTip(tooltip);                   // 鼠标悬停显示完整源文件路径
        tab.putRaw("iframe", true);
        tab.putRaw("iframeUrl", iframeUrl);
        tab.putRaw("icon", iconUrl);                    // F.js 据 icon（图片地址）生成 <img> 图标
        TabStrip1.addChild(tab);
        pc.register(tab);
    }

    /** 文件类型图标地址（res/images/filetype/vs_{类型}.png）。 */
    private static String iconUrl(String ext) {
        return "/res/images/filetype/vs_" + ext + ".png";
    }

    /**
     * 解析移动预览器 URL 指向的真实移动页面路由：{@code /mobile/?file=panel/panel}、
     * {@code /mobile/index?file=panel/panel}、{@code mobile/?file=...} 等 → {@code mobile/panel/panel}
     * （保留大小写，由 canonicalRoute 做大小写/连字符归一）。仅当拼出的 {@code mobile/{file}} 命中已注册路由
     * 才返回（未命中返回 null，调用方按原路由处理）。
     */
    private String resolveMobileRoute(String raw) {
        if (raw == null) {
            return null;
        }
        String s = raw.trim();
        int q = s.indexOf('?');
        if (q < 0) {
            return null;
        }
        String base = s.substring(0, q).trim();
        while (base.startsWith("/")) {
            base = base.substring(1);
        }
        // 上下文路径同样会挡住比对：生产部署在 /java/demo 下，预览器 URL 形如 /java/demo/mobile/?file=...
        base = stripContextPath(base);
        // 只处理移动预览器（含重定向目标 mobile/index）的 URL，其余带查询串的 URL 按原逻辑（截断查询串）处理
        if (!base.equals("mobile") && !base.equals("mobile/") && !base.equals("mobile/index")) {
            return null;
        }
        String query = s.substring(q + 1);
        int f = query.indexOf("file=");
        if (f < 0) {
            return null;
        }
        String file = query.substring(f + "file=".length());
        int amp = file.indexOf('&');
        if (amp >= 0) {
            file = file.substring(0, amp);
        }
        file = file.trim();
        while (file.startsWith("~/")) {
            file = file.substring(2);
        }
        if (file.isEmpty()) {
            return null;
        }
        String mobileRoute = "mobile/" + file;
        return registry.canonicalRoute(mobileRoute) != null ? mobileRoute : null;
    }

    /**
     * 剥掉当前请求的 Servlet 上下文路径前缀：{@code java/demo/block/form} → {@code block/form}（开发态无
     * 上下文路径时原样返回）。线上由 IIS 反向代理挂 {@code /java/demo} 上下文部署，“查看源代码”的
     * {@code files} 首项取自活动选项卡的 iframe URL（带上下文前缀），不剥掉则永远匹配不上注册路由——
     * 弹窗一个选项卡都建不出来（开发态无前缀所以测不出来）。
     */
    private static String stripContextPath(String route) {
        String ctx = currentContextPath();
        if (ctx.isEmpty() || "/".equals(ctx)) {
            return route;
        }
        // routeOf 已去掉前导斜杠，故前缀也按去斜杠形式比对（/java/demo → java/demo/）
        String prefix = (ctx.charAt(0) == '/' ? ctx.substring(1) : ctx) + '/';
        return route.startsWith(prefix) ? route.substring(prefix.length()) : route;
    }

    /** 当前请求的 Servlet 上下文路径（如 {@code /java/demo}）；非 Servlet 环境返回空串。 */
    private static String currentContextPath() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs instanceof ServletRequestAttributes sra) {
            String ctx = sra.getRequest().getContextPath();
            return ctx == null ? "" : ctx;
        }
        return "";
    }

    /** 取路由：去掉协议/主机、兼容的站点根前缀、前导斜杠和查询串。 */
    private static String routeOf(String urlOrRoute) {
        String s = urlOrRoute == null ? "" : urlOrRoute.trim();
        int scheme = s.indexOf("://");
        if (scheme >= 0) {
            int slash = s.indexOf('/', scheme + 3);
            s = slash >= 0 ? s.substring(slash) : "";
        }
        int q = s.indexOf('?');
        if (q >= 0) {
            s = s.substring(0, q);
        }
        if (s.startsWith("~/")) {
            s = s.substring(2);
        }
        while (s.startsWith("/")) {
            s = s.substring(1);
        }
        return s;
    }
}
