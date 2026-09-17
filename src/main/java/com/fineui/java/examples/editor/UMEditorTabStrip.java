package com.fineui.java.examples.editor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.HtmlEditor;
import com.fineui.java.examples.code.PageBase;

/** UMEditor 选项卡（路由 {@code editor/umeditor-tab-strip}）：两个编辑器分布在两个选项卡中；服务端获取两个编辑器的值。 */
@FineUIPage("editor/umeditor-tab-strip")
public class UMEditorTabStrip extends PageBase {

    protected HtmlEditor HtmlEditor1;
    protected HtmlEditor HtmlEditor2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            HtmlEditor1.setText(EditorSample.BASE_TEXT);
            HtmlEditor2.setText(EditorSample.EDITOR2_TEXT);
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showNotifyRaw("编辑器一：" + htmlEncode(HtmlEditor1.getText())
                + "<br/>编辑器二：" + htmlEncode(HtmlEditor2.getText()));
    }
}
