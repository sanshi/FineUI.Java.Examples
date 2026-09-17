package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridRowDataBoundEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 行选择（禁止选择行 + 派生字段驱动，路由 {@code grid/check-all-row-selectable-disabled-attribute-fields}）：
 * 服务端逐行计算派生字段 {@code ResolvedRowSelectable}（入学年份 ≥ 2008 不可选），经数据属性字段灌进客户端
 * {@code rowData.attrs}，由客户端行绑定函数据此把该行标记为不可选（整行变灰）。
 */
@FineUIPage("grid/check-all-row-selectable-disabled-attribute-fields")
public class CheckAllRowSelectableDisabledAttributeFields extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Grid1_RowDataBound(Object sender, GridRowDataBoundEventArgs e) {
        // e.getDataItem() —— 行数据源对象
        int entranceYear = ((Number) e.getFieldValue("EntranceYear")).intValue();
        e.setFieldValue("ResolvedRowSelectable", entranceYear < 2008);
    }
}
