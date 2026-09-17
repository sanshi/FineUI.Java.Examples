package com.fineui.java.examples.accordion;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.List;

/**
 * 手风琴（树控件）页（路由 {@code accordion/tree}）：区域布局左侧放手风琴，手风琴第一个面板内嵌树控件
 * （从 {@code res/menu.xml} 加载），右侧为承载 {@code accordionmainframe} 的 IFrame 区域。首屏加载后递归把
 * 每个叶子节点的链接打开目标（{@code target}）设为该 IFrame 名，点击树节点即在右侧区域打开对应页面。
 */
@FineUIPage("accordion/tree")
public class Tree extends PageBase {

    com.fineui.java.core.controls.Tree treeMenu;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        treeMenu.dataBind(parseMenuXml());
        resolveTreeNode(treeMenu.getNodes());
    }

    /** 递归把叶子节点（无子节点、有链接）的打开目标设为右侧 IFrame。 */
    private void resolveTreeNode(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            if (node.getChildren().isEmpty()) {
                if (node.getHref() != null && !node.getHref().isEmpty()) {
                    node.setTarget("accordionmainframe");
                }
            } else {
                resolveTreeNode(node.getChildren());
            }
        }
    }

    private static Document parseMenuXml() {
        try (InputStream in = Tree.class.getClassLoader().getResourceAsStream("static/res/menu.xml")) {
            if (in == null) {
                throw new IllegalStateException("资源不存在: static/res/menu.xml");
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return factory.newDocumentBuilder().parse(in);
        } catch (Exception e) {
            throw new IllegalStateException("解析菜单 XML 失败", e);
        }
    }
}
