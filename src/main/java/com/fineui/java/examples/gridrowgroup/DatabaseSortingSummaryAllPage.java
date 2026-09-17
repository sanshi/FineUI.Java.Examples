package com.fineui.java.examples.gridrowgroup;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.GridSortEventArgs;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 行分组（数据库分页与排序，分组合计 + 全表合计，路由 {@code grid-row-group/database-sorting-summary-all-page}）：
 * 底部合计行有两行——「平均（当前页）」按当前页数据算、「平均（全部页）」按全部数据算，均由服务端计算后随数据下发。
 */
@FineUIPage("grid-row-group/database-sorting-summary-all-page")
public class DatabaseSortingSummaryAllPage extends PageBase {

    com.fineui.java.core.controls.Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1. 设置总项数（数据库分页初始化时一定要设置总记录数）
        Grid1.setRecordCount(StudentGridData2.count());

        // 2. 获取当前分页数据
        List<Map<String, Object>> pagedData = StudentGridData2.pagedSorted(Grid1.getPageIndex(),
                Grid1.getPageSize(), Grid1.getSortField(), Grid1.getSortDirection());
        Grid1.setDataSource(pagedData);
        Grid1.dataBind();

        // 3. 合计行数据（当前页平均 + 全部页平均）
        Grid1.setSummaryDataArray(getSummaryDataArray(pagedData));
    }

    private List<Map<String, Object>> getSummaryDataArray(List<Map<String, Object>> pagedData) {
        List<Map<String, Object>> summaryArray = new ArrayList<>();
        // 分页合计
        summaryArray.add(calcSummaryRow(pagedData, "平均（当前页）："));
        // 全部合计
        summaryArray.add(calcSummaryRow(StudentGridData2.rows(), "平均（全部页）："));
        return summaryArray;
    }

    private Map<String, Object> calcSummaryRow(List<Map<String, Object>> source, String title) {
        int chineseScoreTotal = 0;
        int mathScoreTotal = 0;
        int rowCount = source.size();
        for (Map<String, Object> row : source) {
            chineseScoreTotal += ((Number) row.get("ChineseScore")).intValue();
            mathScoreTotal += ((Number) row.get("MathScore")).intValue();
        }

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("Major", title);
        summary.put("ChineseScore", String.format("%.2f", (double) (chineseScoreTotal / rowCount)));
        summary.put("MathScore", String.format("%.2f", (double) (mathScoreTotal / rowCount)));
        return summary;
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadData();
    }
}
