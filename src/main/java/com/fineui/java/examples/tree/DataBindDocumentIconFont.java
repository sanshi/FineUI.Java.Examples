package com.fineui.java.examples.tree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Tree;
import com.fineui.java.examples.code.PageBase;

/**
 * 绑定 XML 文档（字体图标）（路由 {@code tree/data-bind-document-icon-font}）：从带 {@code IconFont} 属性的
 * XML 文档构建树，节点显示对应字体图标。
 */
@FineUIPage("tree/data-bind-document-icon-font")
public class DataBindDocumentIconFont extends PageBase {

    Tree Tree1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Tree1.dataBind(TreeXmlData.parse("data/tree/website_iconfont.xml"));
        }
    }
}
