package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 树表格 · 分页 + 合计行 + 列锁定（路由 {@code grid-tree/paging-summary-data-lock-column}）：
 * 在分页 + 合计基础上，开启列锁定与复选框列，各列可锁定到左侧固定不随横向滚动。
 */
@FineUIPage("grid-tree/paging-summary-data-lock-column")
public class PagingSummaryDataLockColumn extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1. 总记录数（顶层节点数）
        Grid1.setRecordCount(getTopNodeCount());
        // 2. 当前页数据：当前页顶层节点 + 其完整子树
        List<Map<String, Object>> pagedData = getPagedData(Grid1.getPageIndex(), Grid1.getPageSize());
        Grid1.setDataSource(pagedData);
        Grid1.dataBind();
        // 3. 合计行数据（当前页）
        Grid1.setSummaryData(getSummaryData(pagedData));
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    private Map<String, Object> getSummaryData(List<Map<String, Object>> paged) {
        int totalSize = 0;
        int totalFileCount = 0;
        for (Map<String, Object> row : paged) {
            Object sizeObj = row.get("Size");
            if (sizeObj != null) {
                totalSize += ((Number) sizeObj).intValue();
            }
            totalFileCount++;
        }

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("Name", "本页合计：");
        summary.put("Size", String.format("%dK (%d %s)", totalSize, totalFileCount, totalFileCount > 1 ? "files" : "file"));
        return summary;
    }

    private int getTopNodeCount() {
        int count = 0;
        for (Map<String, Object> row : FileTreeData.all()) {
            if (toInt(row.get("ParentId")) == -1) {
                count++;
            }
        }
        return count;
    }

    private List<Map<String, Object>> getPagedData(int pageIndex, int pageSize) {
        List<Map<String, Object>> source = FileTreeData.all();

        List<Map<String, Object>> topNodes = new ArrayList<>();
        for (Map<String, Object> row : source) {
            if (toInt(row.get("ParentId")) == -1) {
                topNodes.add(row);
            }
        }

        int recordCount = topNodes.size();
        int rowBegin = pageIndex * pageSize;
        int rowEnd = Math.min((pageIndex + 1) * pageSize, recordCount);

        List<Map<String, Object>> paged = new ArrayList<>();
        for (int i = rowBegin; i < rowEnd; i++) {
            Map<String, Object> top = topNodes.get(i);
            paged.add(top);
            importChildren(source, paged, toInt(top.get("Id")));
        }
        return paged;
    }

    private void importChildren(List<Map<String, Object>> source, List<Map<String, Object>> paged, int parentId) {
        for (Map<String, Object> row : source) {
            if (toInt(row.get("ParentId")) == parentId) {
                paged.add(row);
                importChildren(source, paged, toInt(row.get("Id")));
            }
        }
    }

    private static int toInt(Object value) {
        return ((Number) value).intValue();
    }
}
