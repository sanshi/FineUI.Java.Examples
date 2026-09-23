package com.fineui.java.examples.csp;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextArea;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;

/**
 * HTML 编辑器（路由 {@code csp/html-editor}）：集成第三方 UEditor 富文本编辑器，可获取编辑器内容到多行文本框、
 * 或把多行文本框内容设置回编辑器。本页通过编辑器场景基类关闭 FineUI CSP，演示严格站点中的明确兼容边界。
 */
@FineUIPage("csp/html-editor")
public class HtmlEditor extends ThirdPartyEditorPageBase {

    // 字段类型用全限定名，避免与本示例页类名 HtmlEditor 冲突。
    protected com.fineui.java.core.controls.HtmlEditor HtmlEditor1;
    protected TextArea TextArea1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            HtmlEditor1.setText("FineUI.Java<br>基于 jQuery 的专业 Java Web UI 控件库。<br><br>"
                    + "FineUI 的使命<br>创建 No JavaScript，No CSS 的网站应用程序。<br><br>"
                    + "支持的浏览器<br>Edge、Chrome、Firefox、Safari<br><br>"
                    + "相关链接<br>官网：<a href=\"https://fineui.com/\">https://fineui.com/</a><br><br>");
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        TextArea1.setValue(HtmlEditor1.getText());
    }

    public void Button2_Click(Object sender, EventArgs e) {
        HtmlEditor1.setText(TextArea1.getValue());
    }
}
