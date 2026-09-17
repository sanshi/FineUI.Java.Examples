package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/** 合计行（多行纯客户端合计：最小/最大/平均）（路由 {@code grid/summary-multi-line-client}）：合计由客户端按当前页数据计算（列声明 summary-type / summary-renderer-function）。 */
@FineUIPage("grid/summary-multi-line-client")
public class SummaryMultiLineClient extends PageBase {

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
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
