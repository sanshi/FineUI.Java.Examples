package com.fineui.java.examples.home;

import com.fineui.java.FineUIVersion;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.PageRegistry;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.ControlBase;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.MenuButton;
import com.fineui.java.core.controls.MenuCheckBox;
import com.fineui.java.core.controls.MenuText;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.examples.code.PageBase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 示例站点首页（框架页）模型类，路由 {@code index}（{@code GET /} 重定向到此）。
 *
 * <p>整体是一套应用外壳：左侧 Logo + 示例菜单树，右侧顶部工具栏 + 选项卡工作区；点击左侧菜单在右侧以
 * IFrame 选项卡打开对应示例页。首屏读 cookie 还原用户偏好：设置菜单勾选态（框架页风格 / 显示模式 /
 * 主选项卡标签 / 语言）、搜索文本、仅社区版；菜单树由 {@code res/menu.xml} 递归构建——只保留<b>已注册路由</b>
 * 对应的叶子（其余尚未迁移的自动隐藏），并按“仅社区版 / 搜索文本”过滤、裁掉因此变空的目录。
 */
@FineUIPage("index")
public class Index extends PageBase {

    Tree treeMenu;                        // 菜单树
    MenuCheckBox cbxShowOnlyCommunity;         // 仅显示社区版示例
    MenuButton MenuFramePageStyle;        // 框架页风格（暗色侧栏/顶栏…）
    MenuButton MenuDisplayMode;           // 显示模式（紧凑/普通/大字体…）
    MenuButton MenuMainTabs;              // 主选项卡标签（多/单）
    MenuButton MenuLang;                  // 语言
    TwinTriggerBox ttbxSearch;            // 搜索框
    HiddenField hfExamplesCount;          // 示例总数（客户端读取）
    MenuText menuTextCopyright;           // 版权信息项（首屏把模板里的版本占位符替换成实际版本号）

    private int nodeIndex = 0;
    private boolean showOnlyCommunity = false;
    private String searchText = "";
    private int examplesCount = 0;

    /** 路由注册表，构造器注入（用于按 NavigateUrl 判断示例是否已注册，过滤菜单）。 */
    private final PageRegistry registry;

    public Index(PageRegistry registry) {
        this.registry = registry;
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        String framePageStyle = cookieOrDefault("FramePageStyle", "f-dark-left");
        showOnlyCommunity = "true".equalsIgnoreCase(cookie("ShowOnlyCommunity"));
        String displayMode = cookieOrDefault("DisplayMode", "normal");
        String language = cookieOrDefault("Language", "zh_CN");
        String mainTabs = cookieOrDefault("MainTabs", "multi");
        String st = cookie("SearchText");
        searchText = (st == null || st.isEmpty()) ? "" : URLDecoder.decode(st, StandardCharsets.UTF_8);

        // 按 cookie 还原各设置菜单的勾选态
        setCheckedMenuItem(MenuFramePageStyle, framePageStyle);
        setCheckedMenuItem(MenuDisplayMode, displayMode);
        setCheckedMenuItem(MenuMainTabs, mainTabs);
        setCheckedMenuItem(MenuLang, language);
        if (cbxShowOnlyCommunity != null) {
            cbxShowOnlyCommunity.setChecked(showOnlyCommunity);
        }

        // 还原搜索文本：搜索框平时折叠成一个 24px 的放大镜，有搜索文本时要展开成 200px 并显示清空图标，
        // 否则文本框里的文字看不见、清空图标也没有，用户无法退出搜索状态回到全部示例。
        if (!searchText.isEmpty() && ttbxSearch != null) {
            ttbxSearch.setValue(searchText);
            ttbxSearch.setShowTrigger1(true);
            ttbxSearch.setWidth(200);
            ttbxSearch.setCssClass("searchbox expanded");
        }

        buildTreeMenu();

        if (hfExamplesCount != null) {
            hfExamplesCount.setText(String.valueOf(examplesCount));
        }

        // 版权区（下拉菜单最后一项）：logo + 产品名 + 版本号 + 免费社区/更新记录链接，整块按可信 HTML 原样输出。
        // 在这里拼、而不是写进模板属性：Thymeleaf 会先把属性值转义再交给控件，HTML 标签会变成一串可见文本；
        // 而且模板属性里的 __xxx__ 会被 Thymeleaf 当成预处理语法吃掉，占位符根本留不住。
        if (menuTextCopyright != null) {
            HttpServletRequest req = currentRequest();
            String logoUrl = (req == null ? "" : req.getContextPath()) + "/res/images/newlogo/fineui.png";
            menuTextCopyright.setTextRawHtml(new RawHtml(
                    "<div class='copyright'>"
                            + "<div class='version'><a target='_blank' href='http://fineui.com/'><img src='" + logoUrl + "' alt='logo'/></a>"
                            + "<br/><span>FineUI.Java v" + FineUIVersion.VERSION + "</span></div>"
                            + "<div class='actions'><a target='_blank' href='http://fineui.com/fans/'>免费社区</a>"
                            + "&nbsp;&nbsp;&nbsp;&nbsp;<a target='_blank' href='http://fineui.com/versions/'>更新记录</a></div>"
                            + "</div>"
            ));
        }
    }

    // —— 菜单树构建 ——

    private void buildTreeMenu() {
        Document doc = loadMenuXml();
        if (doc == null || treeMenu == null) {
            return;
        }
        examplesCount = resolveNodes(treeMenu.getNodes(), doc.getDocumentElement().getChildNodes());
    }

    private Document loadMenuXml() {
        try (InputStream in = new ClassPathResource("static/res/menu.xml").getInputStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(in);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 递归解析一层 XML 节点，把可见节点追加进 {@code into}，返回本层（含子孙）可见的<b>叶子</b>数。
     * 叶子可见需同时满足：NavigateUrl 解析为已注册的内部路由、（仅社区版时）非企业版、（有搜索文本时）文本匹配。
     */
    private int resolveNodes(List<TreeNode> into, NodeList xmlNodes) {
        int visibleLeafCount = 0;
        for (int i = 0; i < xmlNodes.getLength(); i++) {
            Node xn = xmlNodes.item(i);
            if (xn.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element el = (Element) xn;
            boolean isLeaf = !hasElementChild(el);

            String text = attr(el, "Text");
            String version = attr(el, "Version");
            String iconFont = attr(el, "IconFont");
            boolean isCorp = "true".equalsIgnoreCase(attr(el, "IsEnterprise"));

            TreeNode node = new TreeNode();
            node.setId("tn_" + (nodeIndex++));

            if (isLeaf) {
                String href = resolveInternalHref(attr(el, "NavigateUrl"));
                if (href == null) {
                    continue;   // 未注册 / 外链 → 隐藏（尚未迁移）
                }
                if (showOnlyCommunity && isCorp) {
                    continue;   // 仅社区版 → 隐藏企业版示例
                }
                if (!searchText.isEmpty() && !text.contains(searchText)) {
                    continue;   // 有搜索文本 → 隐藏不匹配的叶子
                }
                node.setHref(href);
                node.setToolTip(text);
                node.setIconFontName(isCorp ? "_Enterprise" : iconFont);
                node.setText(formatNodeText(text, 0, version));
                node.setTextRaw(true);
                into.add(node);
                visibleLeafCount++;
            } else {
                List<TreeNode> childNodes = new ArrayList<>();
                int childVisible = resolveNodes(childNodes, el.getChildNodes());
                if (childVisible == 0) {
                    continue;   // 目录下无可见叶子 → 隐藏
                }
                node.setSelectable(false);
                node.setIconFontName(iconFont);
                if (!searchText.isEmpty()) {
                    node.setExpanded(true);   // 搜索时展开命中的目录
                }
                node.setText(formatNodeText(text, childVisible, version));
                node.setTextRaw(true);
                for (TreeNode c : childNodes) {
                    node.addChild(c);
                }
                into.add(node);
                visibleLeafCount += childVisible;
            }
        }
        return visibleLeafCount;
    }

    /**
     * NavigateUrl → 首页 IFrame 内部路由 href；空/外链/未注册返回 null（外链暂不纳入菜单）。
     * <b>href 用 Java 自己的规范路由（小写-连字符）</b>——菜单里写什么形态都经注册表解析成规范路由再输出，
     * 而非原样透传：大小写与连字符不敏感（如 {@code /Button/ButtonWidthHeight} 也能命中
     * {@code /button/button-width-height}），允许带前导 {@code /}。
     */
    private String resolveInternalHref(String navigateUrl) {
        if (navigateUrl == null || navigateUrl.isEmpty()) {
            return null;
        }
        if (navigateUrl.startsWith("http://") || navigateUrl.startsWith("https://")) {
            return null;
        }
        String route = navigateUrl;
        if (route.startsWith("~/")) {
            route = route.substring(2);
        } else if (route.startsWith("/")) {
            route = route.substring(1);
        }
        // 带查询串的 NavigateUrl：查询串不属于路由，必须先摘掉再解析，否则 canonicalRoute 认不出来。
        int queryIdx = route.indexOf('?');
        if (queryIdx >= 0) {
            String base = route.substring(0, queryIdx);
            String query = route.substring(queryIdx);        // 含 '?'，原样保留
            // 移动示例是「手机框预览器」链接：mobile/?file=<page>，右侧以 IFrame 打开预览器、内部再嵌 demo。
            // 它要校验的不是 base（预览器自己），而是目标 demo（mobile/<page>，移动首页为 mobile/main）；
            // 未迁移的移动页据此隐藏。
            if (base.equals("mobile/") || base.equals("mobile")) {
                String file = query.startsWith("?file=") ? query.substring("?file=".length()) : "";
                if (file.isEmpty() || registry.canonicalRoute("mobile/" + file) == null) {
                    return null;
                }
                return "/mobile/?file=" + file;
            }
            // 普通页面带查询参数（如 data-model/student-edit?id=101，编辑实体示例要靠它带主键进来）：
            // 按 base 校验注册、用规范路由重建 href，查询串原样附回。
            String canonicalBase = registry.canonicalRoute(base);
            return canonicalBase == null ? null : "/" + canonicalBase + query;
        }
        // 大小写/连字符不敏感解析为登记时的规范路由（Java kebab-case）；未注册返回 null
        String canonical = registry.canonicalRoute(route);
        return canonical == null ? null : "/" + canonical;
    }

    /** 拼接节点文本 HTML（文本 + 目录子项数 + 版本标签；由 index.css 定样式）。 */
    private String formatNodeText(String text, int childCount, String version) {
        StringBuilder sb = new StringBuilder();
        sb.append("<span class=\"text\">").append(text).append("</span>");
        if (childCount > 0) {
            sb.append("<span class=\"menu-child-count\">").append(childCount).append("</span>");
        }
        if (version != null && !version.isEmpty()) {
            sb.append("<span class=\"menu-version\">").append(version).append("</span>");
        }
        return sb.toString();
    }

    // —— 设置菜单勾选态 ——

    /** 把某设置菜单里 {@code data-tag == checkedValue} 的复选项设为勾选、其余取消。 */
    private void setCheckedMenuItem(MenuButton menuButton, String checkedValue) {
        if (menuButton == null) {
            return;
        }
        for (ControlBase menu : menuButton.childrenIn("menu")) {
            for (ControlBase item : menu.childrenIn("items")) {
                if (item instanceof MenuCheckBox cb) {
                    cb.setChecked(checkedValue.equals(cb.getAttributeDataTag()));
                }
            }
        }
    }

    // —— cookie / XML 辅助 ——

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

    private static String cookieOrDefault(String name, String defaultValue) {
        String v = cookie(name);
        return (v == null || v.isEmpty()) ? defaultValue : v;
    }

    private static HttpServletRequest currentRequest() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        return (attrs instanceof ServletRequestAttributes sra) ? sra.getRequest() : null;
    }

    private static boolean hasElementChild(Element el) {
        NodeList kids = el.getChildNodes();
        for (int i = 0; i < kids.getLength(); i++) {
            if (kids.item(i).getNodeType() == Node.ELEMENT_NODE) {
                return true;
            }
        }
        return false;
    }

    private static String attr(Element el, String name) {
        return el.hasAttribute(name) ? el.getAttribute(name) : "";
    }
}
