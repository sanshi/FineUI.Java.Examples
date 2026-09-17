package com.fineui.java.examples.mobile;

import com.fineui.java.core.FineUIPage;
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
 * 菜单数据由 {@link #getMenuSource()} 从 {@code res/menu.xml} 的「移动」子树递归构建成一棵 JSON 树，
 * 经模板注入到 {@code window.MENUSOURCE}，客户端脚本据此逐级加载列表。
 *
 * <p>目录节点只有 {@code text} + {@code children}；叶子节点有 {@code text} + {@code navigateUrl}。叶子的
 * {@code navigateUrl} 由菜单里的 {@code /mobile/?file=x} 改写为直达地址 {@code /mobile/x}——菜单项在 IFrame
 * 内以 {@code _self} 直接导航到对应示例页。
 */
@FineUIPage("mobile/main")
public class Main extends PageBase {

    private List<Map<String, Object>> menuSource;

    /**
     * 移动菜单数据源（供模板经 Thymeleaf JavaScript 内联注入 {@code window.MENUSOURCE}）。
     * 惰性构建并缓存：读 {@code res/menu.xml}「移动」子树 → 递归转成菜单树。解析失败返回空列表。
     */
    public List<Map<String, Object>> getMenuSource() {
        if (menuSource == null) {
            menuSource = buildMenuSource();
        }
        return menuSource;
    }

    private List<Map<String, Object>> buildMenuSource() {
        List<Map<String, Object>> result = new ArrayList<>();
        Document doc = loadMenuXml();
        if (doc == null) {
            return result;
        }
        Element mobileRoot = findMobileRoot(doc.getDocumentElement());
        if (mobileRoot == null) {
            return result;
        }
        // 一级菜单（跳过「移动首页」自身）
        for (Element child : elementChildren(mobileRoot)) {
            String text = attr(child, "Text");
            if ("移动首页".equals(text)) {
                continue;
            }
            result.add(resolveNode(child));
        }
        return result;
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
