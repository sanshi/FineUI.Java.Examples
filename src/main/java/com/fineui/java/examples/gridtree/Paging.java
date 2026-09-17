package com.fineui.java.examples.gridtree;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 树表格 · 顶层节点数据库分页（路由 {@code grid-tree/paging}）：仅对顶层节点（ParentId=-1）分页，
 * 每页只取 {@code page-size} 个顶层节点，但把这些顶层节点的完整子树（全部后代）一并送到客户端，
 * 展开时才能看到子节点。翻页经服务端回发，初始化与翻页都先设总记录数（顶层节点数）。
 */
@FineUIPage("grid-tree/paging")
public class Paging extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 1. 总记录数（数据库分页初始化时必设，客户端据此算总页数）——此处为顶层节点数
        Grid1.setRecordCount(getTopNodeCount());
        // 2. 当前页数据：当前页的顶层节点 + 其完整子树
        Grid1.setDataSource(getPagedData(Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    /** 顶层节点（ParentId=-1）总数。 */
    private int getTopNodeCount() {
        int count = 0;
        for (Map<String, Object> row : FileTreeData.all()) {
            if (toInt(row.get("ParentId")) == -1) {
                count++;
            }
        }
        return count;
    }

    /** 取当前页数据：先按页码切出顶层节点，再把每个顶层节点的完整子树递归加入。 */
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

    /** 递归把 parentId 下的所有后代节点加入结果（保证子树完整）。 */
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
