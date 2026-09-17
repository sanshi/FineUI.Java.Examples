package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 字体图标随机颜色（XML）（路由 {@code tree/tree-icon-font-color-xml}）：从 XML 数据源建树，把每个节点的
 * {@code IconFontColor} 属性转成一个动态 CSS 类，运行时注入页面样式，实现每个节点不同颜色的字体图标；
 * 同时演示服务端读取当前选中的节点。
 */
@FineUIPage("tree/tree-icon-font-color-xml")
public class TreeIconFontColorXml extends PageBase {

    Tree Tree1;
    Label labResult;

    // 需要注入页面的动态 CSS（CSS 类名 → CSS 内容），按类名去重
    private final Map<String, String> dynamicCss = new LinkedHashMap<>();

    public void Page_Load(Object sender, EventArgs e) {
        // PersistNodes（默认 true）：程序化节点随回发往返、服务端从 __FSTATE 恢复（含客户端改动），故只在首屏加载一次；回发时节点已恢复，点击回发 e.getNode()/findNode 依然可用
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Tree1.getNodes().clear();
        dynamicCss.clear();
        Element root = TreeXmlData.parse("data/tree/tree_iconfont_randomcolor.xml").getDocumentElement();
        resolveXmlNodeList(Tree1.getNodes(), root.getChildNodes());

        // 拼接并注入动态 CSS
        StringBuilder css = new StringBuilder();
        for (String content : dynamicCss.values()) {
            css.append(content);
        }
        addCss("dynamicCSSStyles", css.toString());
    }

    private void resolveXmlNodeList(List<TreeNode> nodes, NodeList xmlNodes) {
        for (int i = 0; i < xmlNodes.getLength(); i++) {
            Node xmlNode = xmlNodes.item(i);
            if (xmlNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Element el = (Element) xmlNode;
            TreeNode node = new TreeNode();
            nodes.add(node);

            NamedNodeMap attrs = el.getAttributes();
            for (int a = 0; a < attrs.getLength(); a++) {
                Node attr = attrs.item(a);
                String name = attr.getNodeName();
                String value = attr.getNodeValue();
                if ("IconFontColor".equals(name)) {
                    // 规范化后的 CSS 类名（# 换成 _）
                    String cssName = "tn_color_" + value.replace('#', '_');
                    dynamicCss.putIfAbsent(cssName,
                            String.format(".%1$s .f-tree-folder,.%1$s .f-tree-cell-text{color:%2$s;}", cssName, value));
                    node.setCssClass(cssName);
                } else {
                    node.setPropertyValue(name, value);
                }
            }

            if (el.getChildNodes().getLength() > 0) {
                resolveXmlNodeList(node.getChildren(), el.getChildNodes());
            }
        }
    }

    public void btnGetSelectedNode_Click(Object sender, EventArgs e) {
        String selectedId = Tree1.getSelectedNodeId();
        if (!selectedId.isEmpty()) {
            labResult.setText(String.format("选中的节点：%s（%s）", Tree1.findNode(selectedId).getText(), selectedId));
        } else {
            labResult.setText("没有选中节点");
        }
    }
}
