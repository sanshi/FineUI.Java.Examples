package com.fineui.java.examples.editor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.HtmlEditor;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;

/** CKEditor 富文本编辑器（路由 {@code editor/ckeditor}）：默认只读，客户端按钮切换只读；服务端获取/设置编辑器内容。 */
@FineUIPage("editor/ckeditor")
public class CKEditor extends ThirdPartyEditorPageBase {

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
