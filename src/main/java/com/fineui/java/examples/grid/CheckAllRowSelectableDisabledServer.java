package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowDataBoundEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（禁止选择行，整行变灰，服务端行绑定事件，路由 {@code grid/check-all-row-selectable-disabled-server}）：
 * 把「哪些行不可选」的判断放到服务端行数据绑定事件里（入学年份 &gt;= 2008 不可选）。
 */
@FineUIPage("grid/check-all-row-selectable-disabled-server")
public class CheckAllRowSelectableDisabledServer extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowDataBound(Object sender, GridRowDataBoundEventArgs e) {
        // e.getDataItem() 是该行的数据源对象；入学年份 >= 2008 的行设为不可选
        int entranceYear = ((Number) e.getFieldValue("EntranceYear")).intValue();
        e.setRowSelectable(entranceYear < 2008);
    }
}
