package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 下拉表格（输入框过滤，路由 {@code drop-down-box/grid-search}）：弹出面板内的表格顶部工具栏放一个双触发按钮文本框，
 * 输入姓名关键字点搜索走服务端过滤 + 重新分页绑定，点清除取消过滤。
 */
@FineUIPage("drop-down-box/grid-search")
public class GridSearch extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    TwinTriggerBox ttbSearch;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        String keyword = ttbSearch.getValue();
        List<Map<String, Object>> filtered = search(keyword);
        // 1.设置总项数（带搜索词的过滤后总数）
        Grid1.setRecordCount(filtered.size());
        // 2.获取当前分页数据（带搜索词分页）
        Grid1.setDataSource(page(filtered, Grid1.getPageIndex(), Grid1.getPageSize()));
        Grid1.dataBind();
    }

    /** 按姓名关键字过滤（空关键字返回全部）。 */
    private List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> all = StudentGridData2.rows();
        if (keyword == null || keyword.isEmpty()) {
            return all;
        }
        List<Map<String, Object>> out = new ArrayList<>();
        for (Map<String, Object> row : all) {
            Object name = row.get("Name");
            if (name != null && name.toString().contains(keyword)) {
                out.add(row);
            }
        }
        return out;
    }

    /** 取某一页（pageIndex 越界时裁到最后一页，避免过滤后当前页为空）。 */
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

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadData();
    }

    public void ttbSearch_Trigger1Click(Object sender, EventArgs e) {
        // 清除按钮：清空关键字、隐藏清除图标、重新加载
        ttbSearch.setValue("");
        ttbSearch.setShowTrigger1(false);
        loadData();
    }

    public void ttbSearch_Trigger2Click(Object sender, EventArgs e) {
        // 搜索按钮
        ttbSearch.setShowTrigger1(false);
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
