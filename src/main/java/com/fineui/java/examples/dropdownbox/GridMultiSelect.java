package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（多选，初始值）演示页（路由 {@code drop-down-box/grid-multi-select}）：下拉框 {@code enable-multi-select=true}、
 * 弹出面板内是复选多选表格，初始值 {@code values=105,108} 反向勾选对应行；多选文本以逗号拼接显示。
 */
@FineUIPage("drop-down-box/grid-multi-select")
public class GridMultiSelect extends PageBase {

    DropDownBox DropDownBox1;
    com.fineui.java.core.controls.Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
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
