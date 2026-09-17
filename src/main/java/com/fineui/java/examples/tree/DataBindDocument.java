package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 绑定 XML 文档（路由 {@code tree/data-bind-document}）：从 XML 文档构建树；服务端可清空树、重新绑定树（运行时重载）。
 */
@FineUIPage("tree/data-bind-document")
public class DataBindDocument extends PageBase {

    Tree Tree1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Tree1.dataBind(TreeXmlData.parse("data/tree/website.xml"));
        }
    }

    public void btnClear_Click(Object sender, EventArgs e) {
        Tree1.dataBind(null);
        Tree1.loadData(Tree1.getNodes());   // 空节点 → 客户端清空树
    }

    public void btnReBind_Click(Object sender, EventArgs e) {
        Tree1.dataBind(TreeXmlData.parse("data/tree/website.xml"));
        Tree1.loadData(Tree1.getNodes());   // 重新绑定 → 客户端整树重载
    }
}
