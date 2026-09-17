package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowDataBoundEventArgs;
import com.fineui.java.core.controls.Grid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 服务端行绑定格式化字段（路由 {@code grid/row-data-bound-server}）：用服务端行绑定事件把注册日期格式化为中文、
 * 性别整数转男/女（服务端只能改数据字段的值，不能改列）。另演示清空数据与重新绑定。
 */
@FineUIPage("grid/row-data-bound-server")
public class RowDataBoundServer extends PageBase {

    private static final DateTimeFormatter CN_DATE = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowDataBound(Object sender, GridRowDataBoundEventArgs e) {
        // 注意：getFieldValue 的参数是数据字段名（DataField），不是列标识符（ColumnID）
        LocalDate logTime = (LocalDate) e.getFieldValue("LogTime");
        e.setFieldValue("LogTime", logTime.format(CN_DATE));

        int gender = ((Number) e.getFieldValue("Gender")).intValue();
        e.setFieldValue("Gender", gender == 1 ? "男" : "女");
    }

    public void btnClearData_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(null);
        Grid1.dataBind();
    }

    public void btnRebindData_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(StudentGridData2.rows());
        Grid1.dataBind();
    }
}
