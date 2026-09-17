package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 多选标签（下拉表格）演示页（路由 {@code drop-down-box/tags-grid-multi-select}）：下拉框多选、标签模式，弹出面板内是复选
 * 多选表格（无 data-control-id，F.js 自动认表格为数据控件），初始值 {@code values=105,108} 反向勾选对应行、文本由
 * 表格 {@code data-text-field=Name} 反查；「获取下拉框的选中值」回发读取；「重置表单」客户端复位。
 */
@FineUIPage("drop-down-box/tags-grid-multi-select")
public class TagsGridMultiSelect extends PageBase {

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
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
