package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 表头属性与编辑器共存：表头显示必填红星，单元格仍可编辑，保存后在当前会话内保留修改。 */
@FineUIPage("grid-editor/header-attributes")
public class HeaderAttributes extends PageBase {

    private static final String SESSION_KEY = "GridEditor.HeaderAttributes";
    private static final String[] EDITABLE_COLUMNS = {"Code", "Name", "Remark"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> rows = sourceData();
        int modifiedCount = 0;

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("modified".equals(String.valueOf(modifiedRow.get("status")))) {
                String rowId = String.valueOf(modifiedRow.get("id"));
                updateDataRow(modifiedRow, rowId, rows, EDITABLE_COLUMNS);
                modifiedCount++;
            }
        }

        // 用会话模拟保存到数据库；重新加载数据后，表头标记与编辑器仍按页面声明保留。
        session().setAttribute(SESSION_KEY, rows);
        bindGrid();
        labResult.setText("已保存 " + modifiedCount + " 行修改，可继续编辑或刷新页面查看。");
        showNotify("数据保存成功");
    }

    private void bindGrid() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> rows = new ArrayList<>();
            rows.add(new LinkedHashMap<>(Map.of(
                    "Id", 101,
                    "Code", "A001",
                    "Name", "键盘",
                    "Remark", "编号和名称可以编辑")));
            rows.add(new LinkedHashMap<>(Map.of(
                    "Id", 102,
                    "Code", "A002",
                    "Name", "鼠标",
                    "Remark", "两列表头都有必填红星")));
            rows.add(new LinkedHashMap<>(Map.of(
                    "Id", 103,
                    "Code", "A003",
                    "Name", "显示器",
                    "Remark", "备注可以留空")));
            session().setAttribute(SESSION_KEY, rows);
            return rows;
        }
        return (List<Map<String, Object>>) cached;
    }
}
