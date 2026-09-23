package com.fineui.java.examples.editor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.HtmlEditor;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;

/** TinyMCE 窗体内编辑器（路由 {@code editor/tinymce-window}）：编辑器位于非模态窗体中；服务端获取/设置编辑器内容。 */
@FineUIPage("editor/tinymce-window")
public class TinyMCEWindow extends ThirdPartyEditorPageBase {

    protected HtmlEditor HtmlEditor1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            HtmlEditor1.setText(EditorSample.BASE_TEXT);
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        String v = HtmlEditor1.getText();
        if (v == null || v.isEmpty()) {
            showNotify("编辑器内容为空！");
        } else {
            showNotifyRaw(v);
        }
    }

    public void Button2_Click(Object sender, EventArgs e) {
        HtmlEditor1.setText("<p><strong>FineUI.Java</strong> - Java 企业级全栈 UI 框架。</p>");
    }
}
