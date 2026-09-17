package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;

import java.util.List;
import java.util.Map;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/** 合计行（Flow 位置，随内容滚动）（路由 {@code grid/summary-scrollbar-flow}）：合计行显示当前页的学费/学杂费合计，翻页时按当前页重算。 */
@FineUIPage("grid/summary-scrollbar-flow")
public class SummaryScrollbarFlow extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setRecordCount(StudentGridData2.count());
        List<Map<String, Object>> paged = StudentGridData2.paged(Grid1.getPageIndex(), Grid1.getPageSize());
        Grid1.setDataSource(paged);
        Grid1.dataBind();
        // 当前页合计
        Grid1.setSummaryData(SummaryCalc.feeExtraFee(paged));
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
