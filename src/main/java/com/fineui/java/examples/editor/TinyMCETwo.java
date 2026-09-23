package com.fineui.java.examples.editor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.HtmlEditor;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;
import com.fineui.java.examples.form.FormSummary;

/** TinyMCE 双编辑器（路由 {@code editor/tinymce-two}）：正文/摘要两个编辑器；“更新文章摘要”服务端把正文去标签后回填摘要。 */
@FineUIPage("editor/tinymce-two")
public class TinyMCETwo extends ThirdPartyEditorPageBase {

    protected TextBox tbxTitle;
    protected HtmlEditor HtmlEditor1;
    protected HtmlEditor HtmlEditor2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            HtmlEditor1.setText(EditorSample.BASE_TEXT);
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        if (HtmlEditor1.getText() == null || HtmlEditor1.getText().isEmpty()) {
            showNotify("文章正文不能为空！");
        } else {
            showNotifyRaw("文章标题：" + htmlEncode(tbxTitle.getValue()) + "<br/>文章正文："
                    + htmlEncode(HtmlEditor1.getText()) + "<br/>文章摘要：" + htmlEncode(HtmlEditor2.getText()));
        }
    }

    public void Button2_Click(Object sender, EventArgs e) {
        String content = HtmlEditor1.getText().replaceAll("<[^>]+>|</[^>]+>", "");
        if (content.length() > 100) {
            content = content.substring(0, 97) + "...";
        }
        HtmlEditor2.setText(content);
    }
}
