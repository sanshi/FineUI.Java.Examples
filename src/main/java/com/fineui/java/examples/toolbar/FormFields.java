package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DatePicker;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 工具栏中的表单字段（路由 {@code toolbar/form-fields}）：表格工具栏内放两个日期选择器
 * （比较校验：结束日期须晚于开始日期）与查询/重置按钮——查询校验通过后加载数据，
 * 重置清空起止时间并清空表格。
 */
@FineUIPage("toolbar/form-fields")
public class FormFields extends PageBase {

    DatePicker dpStartDate;
    DatePicker dpEndDate;
    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnClearDate_Click(Object sender, EventArgs e) {
        dpStartDate.reset();
        dpEndDate.reset();
        Grid1.setDataSource(null);
        Grid1.dataBind();
    }

    public void btnSearch_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }
}
