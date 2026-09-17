package com.fineui.java.examples.gridlockcolumn;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;
import com.fineui.java.examples.grid.SummaryCalc;

/** 右侧列锁定 + 合计行（路由 {@code grid-lock-column/right-summary}）：列锁定 + 数据库分页 + 全部合计。 */
@FineUIPage("grid-lock-column/right-summary")
public class RightSummary extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
        Grid1.setSummaryData(SummaryCalc.feeExtraFee(StudentGridData2.rows()));   // 全部合计
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
