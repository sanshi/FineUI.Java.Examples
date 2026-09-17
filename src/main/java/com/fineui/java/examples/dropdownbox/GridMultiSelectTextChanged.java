package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（多选，文本改变事件）演示页（路由 {@code drop-down-box/grid-multi-select-text-changed}）：多选下拉表格
 * 绑定 {@code on-text-changed}，勾选变化导致文本改变时立即回发触发；初始值 {@code values=105,108}。
 */
@FineUIPage("drop-down-box/grid-multi-select-text-changed")
public class GridMultiSelectTextChanged extends PageBase {

    DropDownBox DropDownBox1;
    com.fineui.java.core.controls.Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void DropDownBox1_TextChanged(Object sender, EventArgs e) {
        showResult();
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        showResult();
    }

    private void showResult() {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
