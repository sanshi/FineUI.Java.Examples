package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（客户端全选，路由 {@code drop-down-box/grid-select-all-client}）：弹出面板内的表格被 Panel 包裹，
 * 面板底部工具栏按钮用客户端 listener 调用表格客户端 API（全选/按专业选中/保持已选）后再 syncToBox 同步到下拉框。
 */
@FineUIPage("drop-down-box/grid-select-all-client")
public class GridSelectAllClient extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
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
