package com.fineui.java.examples.dropdownbox;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownBox;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.RadioButtonList;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 始终显示弹出面板演示页（路由 {@code drop-down-box/grid-layout-always-display}）：下拉框通过外部面板 ID 关联一个始终展开
 * （不弹出/收起）的面板，面板内是搜索表单 + 数据库分页 + 服务端排序的多选表格。搜索、翻页、排序均回发重绑。
 */
@FineUIPage("drop-down-box/grid-layout-always-display")
public class GridLayoutAlwaysDisplay extends PageBase {

    DropDownBox DropDownBox1;
    Grid Grid1;
    TwinTriggerBox ttbSearch;
    RadioButtonList rblAtSchool;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    private void bindGrid() {
        List<Map<String, Object>> source = getSource();
        Grid1.setRecordCount(source.size());
        Grid1.setDataSource(getPaged(source));
        Grid1.dataBind();
    }

    private List<Map<String, Object>> getSource() {
        List<Map<String, Object>> all = StudentGridData2.sortedRows(Grid1.getSortField(), Grid1.getSortDirection());

        String keyword = ttbSearch.getValue();
        keyword = keyword == null ? "" : keyword.trim();
        boolean nameFilter = !keyword.isEmpty() && ttbSearch.isShowTrigger1();

        String atSchool = rblAtSchool.getSelectedValue();
        boolean atSchoolFilter = atSchool != null && !atSchool.isEmpty() && !"-1".equals(atSchool);

        List<Map<String, Object>> filtered = new ArrayList<>();
        for (Map<String, Object> row : all) {
            if (nameFilter && !String.valueOf(row.get("Name")).contains(keyword)) {
                continue;
            }
            if (atSchoolFilter) {
                String rowAtSchool = Boolean.TRUE.equals(row.get("AtSchool")) ? "1" : "0";
                if (!rowAtSchool.equals(atSchool)) {
                    continue;
                }
            }
            filtered.add(row);
        }
        return filtered;
    }

    private List<Map<String, Object>> getPaged(List<Map<String, Object>> source) {
        int pageSize = Grid1.getPageSize();
        int from = Grid1.getPageIndex() * pageSize;
        if (from >= source.size()) {
            return new ArrayList<>();
        }
        int to = Math.min(source.size(), from + pageSize);
        return new ArrayList<>(source.subList(from, to));
    }

    public void Grid1_PageIndexChanged(Object sender, EventArgs e) {
        bindGrid();
    }

    public void Grid1_Sort(Object sender, EventArgs e) {
        bindGrid();
    }

    public void ttbSearch_Trigger1Click(Object sender, EventArgs e) {
        ttbSearch.setValue("");
        ttbSearch.setShowTrigger1(false);
        bindGrid();
    }

    public void ttbSearch_Trigger2Click(Object sender, EventArgs e) {
        ttbSearch.setShowTrigger1(true);
        bindGrid();
    }

    public void rblAtSchool_SelectedIndexChanged(Object sender, EventArgs e) {
        bindGrid();
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
