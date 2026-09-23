package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.TextArea;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;

/**
 * HTML 编辑器（路由 {@code form/html-editor}）：集成 UMEditor 富文本编辑器，
 * 可获取编辑器内容到多行文本框、或把多行文本框内容设置回编辑器。富文本编辑器可在
 * UMEditor / UEditor / CKEditor / TinyMCE 之间通过控件属性简单切换。
 */
@FineUIPage("form/html-editor")
public class HtmlEditor extends ThirdPartyEditorPageBase {

    // 字段类型用全限定名，避免与本示例页类名 HtmlEditor 冲突。
    protected com.fineui.java.core.controls.HtmlEditor HtmlEditor1;
    protected TextArea TextArea1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            HtmlEditor1.setText("FineUI.Pro<br>基于 jQuery 的专业 Web UI 控件库。<br><br>"
                    + "FineUI 的使命<br>创建 No JavaScript，No CSS 的网站应用程序。<br><br>"
                    + "支持的浏览器<br>Chrome、Firefox、Safari<br><br>"
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
