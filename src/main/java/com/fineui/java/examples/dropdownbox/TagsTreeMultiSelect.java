package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 多选标签（下拉树）演示页（路由 {@code drop-down-box/tags-tree-multi-select}）：下拉框多选、标签模式，弹出面板内直接放一个
 * 多选树控件（无 data-control-id，F.js 自动认树为数据控件）；选中节点以可删标签渲染，初始值 {@code values=henan,anhui}
 * 反向选中对应节点、文本由树节点反查；「获取下拉框的选中值」回发读取当前文本与全部选中值。
 */
@FineUIPage("drop-down-box/tags-tree-multi-select")
public class TagsTreeMultiSelect extends PageBase {

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
