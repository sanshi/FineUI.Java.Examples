package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（单选，文本改变事件）演示页（路由 {@code drop-down-box/grid-text-changed}）：下拉框绑定
 * {@code on-text-changed}，选中表格行导致文本变化时立即回发触发；「获取下拉框的选中值」按钮同样展示结果。
 */
@FineUIPage("drop-down-box/grid-text-changed")
public class GridTextChanged extends PageBase {

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
