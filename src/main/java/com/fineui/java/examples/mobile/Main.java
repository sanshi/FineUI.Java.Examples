package com.fineui.java.examples.mobile;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 移动示例首页（菜单壳，路由 {@code mobile/main}）。运行在预览器的手机 IFrame 内部，是移动 App 的「首页」。
 *
 * <p>三级下滑菜单：一/二/三级菜单各一个铺满视口的面板 + 数据列表，靠 hashchange 与滑动动画在层级间切换。
 * 菜单数据在 Page_Init 中从 {@code res/menu.xml} 的「移动」子树递归构建成一棵 JSON 树，
 * 页面通过非执行 JSON 数据块交给客户端脚本逐级加载列表。
 *
 * <p>目录节点只有 {@code text} + {@code children}；叶子节点有 {@code text} + {@code navigateUrl}。叶子的
 * {@code navigateUrl} 由菜单里的 {@code /mobile/?file=x} 改写为直达地址 {@code /mobile/x}——菜单项在 IFrame
 * 内以 {@code _self} 直接导航到对应示例页。
 */
@FineUIPage("mobile/main")
public class Main extends PageBase {

    private String menuDataJson = "[]";

    /** 页面模板读取的菜单 JSON；Page_Init 已转义菜单文字，避免内容提前结束数据标签。 */
    public String getMenuDataJson() {
        return menuDataJson;
    }

    public void Page_Init(Object sender, EventArgs e) {
        // 页面模板会读取 menuDataJson，先从 XML 组装菜单，再编码成非执行 JSON 数据。
        List<Map<String, Object>> menuSource = new ArrayList<>();
        Document doc = loadMenuXml();
        if (doc != null) {
            Element mobileRoot = findMobileRoot(doc.getDocumentElement());
            if (mobileRoot != null) {
                // 一级菜单跳过「移动首页」自身，只把可打开的菜单项交给页面。
                for (Element child : elementChildren(mobileRoot)) {
                    String text = attr(child, "Text");
                    if ("移动首页".equals(text)) {
                        continue;
                    }
                    menuSource.add(resolveNode(child));
                }
            }
        }
        menuDataJson = Json.encodeForScriptData(menuSource);
    }

    /** 递归把一个 XML 菜单节点转成菜单对象：有子节点 → 目录 {text, children}；否则 → 叶子 {text, navigateUrl}。 */
    private Map<String, Object> resolveNode(Element node) {
        Map<String, Object> menu = new LinkedHashMap<>();
        menu.put("text", attr(node, "Text"));

        List<Element> children = elementChildren(node);
        if (!children.isEmpty()) {
            List<Map<String, Object>> subMenus = new ArrayList<>();
            for (Element sub : children) {
                subMenus.add(resolveNode(sub));
            }
            menu.put("children", subMenus);
        } else {
            menu.put("navigateUrl", rewriteNavigateUrl(attr(node, "NavigateUrl")));
        }
        return menu;
    }

    /** {@code /mobile/?file=button/button} → {@code /mobile/button/button}（去 {@code /?file=} 拼成直达地址）。 */
    private static String rewriteNavigateUrl(String navigateUrl) {
        if (navigateUrl == null || navigateUrl.isEmpty()) {
            return "";
        }
        String url = navigateUrl.replace("/?file=", "/");
        if (url.startsWith("~/")) {
            url = "/" + url.substring(2);
        }
        return url;
    }

    // —— XML 辅助 ——

    private static Document loadMenuXml() {
        try (InputStream in = new ClassPathResource("static/res/menu.xml").getInputStream()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(in);
        } catch (Exception ex) {
            return null;
        }
    }

    /** 在根节点下找 Text="移动" 的一级节点。 */
    private static Element findMobileRoot(Element root) {
        for (Element el : elementChildren(root)) {
            if ("移动".equals(attr(el, "Text"))) {
                return el;
            }
        }
        return null;
    }

    private static List<Element> elementChildren(Element parent) {
        List<Element> list = new ArrayList<>();
        NodeList kids = parent.getChildNodes();
        for (int i = 0; i < kids.getLength(); i++) {
            Node n = kids.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                list.add((Element) n);
            }
        }
        return list;
    }

    private static String attr(Element el, String name) {
        return el.hasAttribute(name) ? el.getAttribute(name) : "";
    }
}
