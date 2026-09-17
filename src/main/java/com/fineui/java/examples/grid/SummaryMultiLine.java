package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/** 多行合计（路由 {@code grid/summary-multi-line}）：两行合计——第一行当前页合计、第二行全部合计。 */
@FineUIPage("grid/summary-multi-line")
public class SummaryMultiLine extends PageBase {

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

        List<Map<String, Object>> array = new ArrayList<>();
        array.add(SummaryCalc.feeExtraFeeTitled(paged, "当前页合计："));                 // 第一行：当前页合计
        array.add(SummaryCalc.feeExtraFeeTitled(StudentGridData2.rows(), "全部合计："));  // 第二行：全部合计
        Grid1.setSummaryDataArray(array);
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }
}
