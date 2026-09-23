package com.fineui.java.examples.editor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.HtmlEditor;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;

/** UEditor 自定义配置（路由 {@code editor/ueditor-config}）：通过 editorOptions 自定义工具栏等；服务端获取/设置内容。 */
@FineUIPage("editor/ueditor-config")
public class UEditorConfig extends ThirdPartyEditorPageBase {

    protected HtmlEditor HtmlEditor1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            HtmlEditor1.setText(EditorSample.BASE_TEXT);
        java.util.Map<String, Object> opts = new java.util.LinkedHashMap<>();
        opts.put("lang", "en");
        opts.put("toolbars", java.util.List.of(java.util.List.of(
                "bold", "italic", "underline", "strikethrough", "|",
                "insertorderedlist", "insertunorderedlist", "|",
                "justifyleft", "justifycenter", "justifyright", "|",
                "link", "unlink", "|", "source")));
        HtmlEditor1.setEditorOptions(opts);
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
