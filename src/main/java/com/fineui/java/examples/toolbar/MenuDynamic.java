package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.ControlBase;
import com.fineui.java.core.controls.Menu;
import com.fineui.java.core.controls.MenuHyperLink;
import com.fineui.java.core.controls.Toolbar;
import com.fineui.java.examples.code.PageBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 动态添加工具栏菜单页（路由 {@code toolbar/menu-dynamic}）：服务端从 XML 文件读取菜单结构，
 * 在工具栏里动态创建一个按钮，其下拉菜单由 XML 递归构建（叶子为超链接、在新标签打开）。
 *
 * <p>动态创建的控件仅首屏构建（{@code if (!isPostBack())}）：本页无回发交互；一般地，回发时客户端会把动态控件
 * 随 {@code __FSTATE} 回带、服务端据此重建控件树，故无需（也不应）在回发时重复创建。
 */
@FineUIPage("toolbar/menu-dynamic")
public class MenuDynamic extends PageBase {

    Toolbar Toolbar1;

    private int idSeq = 0;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Document doc = parseMenuXml();
        Element root = doc.getDocumentElement();          // <menu>
        Element first = firstElementChild(root);          // 中国科学技术大学
        if (first == null) {
            return;
        }
        Button btn = new Button();
        btn.setId("dynBtn");
        btn.setText(first.getAttribute("text"));
        Toolbar1.addChild(btn);
        resolveMenu(btn, first);
    }

    /** 递归：为 owner 创建下拉菜单，把 ownerNode 的每个带 navigateurl 的子节点建成超链接加入该菜单。 */
    private void resolveMenu(ControlBase owner, Element ownerNode) {
        Menu menu = new Menu();
        menu.setId("dynMenu_" + (idSeq++));
        owner.addChild(menu, "menu");

        NodeList children = ownerNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            if (n.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element el = (Element) n;
            if (!el.hasAttribute("navigateurl")) {   // 仅处理带 navigateurl 的节点
                continue;
            }
            String url = el.getAttribute("navigateurl");
            MenuHyperLink lnk = new MenuHyperLink();
            lnk.setId("dynLnk_" + (idSeq++));
            lnk.setText(el.getAttribute("text"));
            lnk.setNavigateUrl(url);
            lnk.setTarget("_blank");
            menu.addChild(lnk);

            if (firstElementChild(el) != null) {
                resolveMenu(lnk, el);
            }
        }
    }

    private static Element firstElementChild(Element parent) {
        NodeList children = parent.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node n = children.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                return (Element) n;
            }
        }
        return null;
    }

    private static Document parseMenuXml() {
        try (InputStream in = MenuDynamic.class.getClassLoader().getResourceAsStream("static/content/toolbar/menu.xml")) {
            if (in == null) {
                throw new IllegalStateException("资源不存在: static/content/toolbar/menu.xml");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return factory.newDocumentBuilder().parse(in);
        } catch (Exception e) {
            throw new IllegalStateException("解析工具栏菜单 XML 失败", e);
        }
    }
}
