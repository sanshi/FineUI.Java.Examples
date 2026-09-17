package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;
import com.fineui.java.examples.gridfilter.FilterMatchers;
import com.fineui.java.examples.gridfilter.FilteredTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下拉表格（下拉列表过滤，表头菜单过滤，路由 {@code drop-down-box/grid-search-drop-down-list}）：
 * 工具栏下拉列表（是否在校）联动过滤 + 所学专业列表头菜单过滤（等于/包含/开始于/结束于）叠加，两者一起作用于数据库分页。
 */
@FineUIPage("drop-down-box/grid-search-drop-down-list")
public class GridSearchDropDownList extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    DropDownList ddlAtSchool;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        List<Map<String, Object>> source = getSource();
        // 1.设置总项数（过滤后总数）
        Grid1.setRecordCount(source.size());
        // 2.获取当前分页数据
        Grid1.setDataSource(page(source, Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    /** 应用「工具栏下拉列表过滤」+「表头菜单过滤」后的数据。 */
    private List<Map<String, Object>> getSource() {
        List<Map<String, Object>> source = StudentGridData2.rows();

        // 表格工具栏中的下拉列表过滤项（是否在校）
        String atSchool = ddlAtSchool.getSelectedValue();
        if (atSchool != null && !atSchool.isEmpty() && !"-1".equals(atSchool)) {
            boolean want = "1".equals(atSchool);
            List<Map<String, Object>> filtered = new ArrayList<>();
            for (Map<String, Object> row : source) {
                if (Boolean.TRUE.equals(row.get("AtSchool")) == want) {
                    filtered.add(row);
                }
            }
            source = filtered;
        }

        // 表头菜单的过滤项（所学专业列，带操作符）
        return FilteredTable.getFilteredRows(source, Grid1, this::filterDataRowItem);
    }

    private boolean filterDataRowItem(Object sourceValue, GridColumnFilteredItem item, String columnId) {
        if ("Major".equals(columnId)) {
            return FilterMatchers.text(item.getOperator(), sourceValue, item.getValue());
        }
        return false;
    }

    /** 取某一页（pageIndex 越界时裁到最后一页）。 */
    private static List<Map<String, Object>> page(List<Map<String, Object>> src, int pageIndex, int pageSize) {
        if (pageSize <= 0 || src.isEmpty()) {
            return new ArrayList<>(src);
        }
        int maxPage = Math.max(0, (src.size() - 1) / pageSize);
        int idx = Math.min(Math.max(0, pageIndex), maxPage);
        int from = idx * pageSize;
        int to = Math.min(src.size(), from + pageSize);
        return new ArrayList<>(src.subList(from, to));
    }

    public void ddlAtSchool_SelectedIndexChanged(Object sender, EventArgs e) {
        loadData();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    public void Grid1_FilterChanged(Object sender, EventArgs e) {
        loadData();
    }

    public void btnGetSelection_Click(Object sender, EventArgs e) {
        String text = DropDownBox1.getText();
        if (text != null && !text.isEmpty()) {
            labResult.setText(String.format("下拉框文本：%s（值：%s）", text, String.join(", ", DropDownBox1.getValues())));
        } else {
            labResult.setText("下拉框为空");
        }
    }
}
