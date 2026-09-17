package com.fineui.java.examples.dropdownbox;

import java.util.List;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 下拉表格（多选，初始值，后台更新选中值）演示页（路由 {@code drop-down-box/grid-multi-select-update-value}）：
 * 「更新下拉框的值为[陈飞,黄婷婷,张三石]」按钮在服务端改多选值集合，文本置空由客户端根据表格行数据重算。
 */
@FineUIPage("drop-down-box/grid-multi-select-update-value")
public class GridMultiSelectUpdateValue extends PageBase {

    DropDownBox DropDownBox1;
    com.fineui.java.core.controls.Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void btnUpdateValue_Click(Object sender, EventArgs e) {
        // 1. 对于未分页的表格，可以将文本置空以便客户端重新计算
        // 2. 对于分页的表格，一定要手工设置文本，否则客户端无法取到值对应的文本
        DropDownBox1.setValues(List.of("102", "107", "112"));
        DropDownBox1.setText(null);
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
