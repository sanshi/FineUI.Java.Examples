package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义下拉树（复选框，启用级联选择）演示页（路由 {@code drop-down-box/tree-checkbox-cascade}）：与复选框版类似，
 * 但树启用级联勾选（勾父自动勾子孙）；面板打开时按下拉框当前值递归勾选并把全部勾选同步回下拉框，
 * 勾选变化时经页面脚本（延迟等级联完成后）回写下拉框；「获取下拉框的选中值」回发读取当前文本与全部选中值。
 */
@FineUIPage("drop-down-box/tree-checkbox-cascade")
public class TreeCheckboxCascade extends PageBase {

    DropDownBox DropDownBox1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
