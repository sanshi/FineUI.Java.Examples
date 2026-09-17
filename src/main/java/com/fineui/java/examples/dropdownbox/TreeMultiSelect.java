package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 下拉树（多选）演示页（路由 {@code drop-down-box/tree-multi-select}）：下拉框开启多选，弹出面板内放一个
 * 多选树控件；勾选/选中的节点值与文本同步进下拉框；「获取下拉框的选中值」回发读取当前文本与全部选中值。
 */
@FineUIPage("drop-down-box/tree-multi-select")
public class TreeMultiSelect extends PageBase {

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
