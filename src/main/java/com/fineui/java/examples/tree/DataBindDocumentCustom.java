package com.fineui.java.examples.tree;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.TreeNodeEventArgs;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.core.controls.TreeNode;
import com.fineui.java.examples.code.PageBase;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

/**
 * 自定义解析 XML（路由 {@code tree/data-bind-document-custom}）：手工遍历 XML 文档创建节点，把 XML 的
 * {@code Highlight} 属性转化为节点的 {@code CssClass}；点击节点回发，回填被点击节点信息（含是否高亮）。
 */
@FineUIPage("tree/data-bind-document-custom")
public class DataBindDocumentCustom extends PageBase {

    Tree Tree1;
    Label labResult;

    public void Page_Load(Object sender, com.fineui.java.core.EventArgs e) {
        // PersistNodes（默认 true）：程序化节点随回发往返、服务端从 __FSTATE 恢复（含客户端改动），故只在首屏加载一次；回发时节点已恢复，点击回发 e.getNode()/findNode 依然可用
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Tree1.getNodes().clear();
        Element root = TreeXmlData.parse("data/tree/website.xml").getDocumentElement();
        resolveXmlNodeList(Tree1.getNodes(), root.getChildNodes());
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
                if ("Highlight".equals(attr.getNodeName())) {
                    node.setCssClass("highlight");
                } else {
                    node.setPropertyValue(attr.getNodeName(), attr.getNodeValue());
                }
            }

            if (el.getChildNodes().getLength() > 0) {
                resolveXmlNodeList(node.getChildren(), el.getChildNodes());
            }
        }
    }

    public void Tree1_NodeClick(Object sender, TreeNodeEventArgs e) {
        TreeNode node = e.getNode();
        String highlight = "highlight".equals(node.getCssClass()) ? " - 高亮显示" : "";
        labResult.setText(String.format("你点击了树节点：%s（%s）%s", node.getText(), e.getNodeID(), highlight));
    }
}
