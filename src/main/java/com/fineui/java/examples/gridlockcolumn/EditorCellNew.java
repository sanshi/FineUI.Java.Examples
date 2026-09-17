package com.fineui.java.examples.gridlockcolumn;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.RawHtml;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 单元格编辑 + 新增数据（路由 {@code grid-lock-column/editor-cell-new}）：锁定姓名列、
 * 双击编辑各列，工具栏「新增数据」在客户端插入新行、「重置表格数据」回退所有未保存修改；
 * 保存回发读回修改并展示。
 */
@FineUIPage("grid-lock-column/editor-cell-new")
public class EditorCellNew extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = StudentGridData.rows();
        // 把客户端修改（modified）合并回数据源、新增行（newadded）插入
        int maxId = source.stream().mapToInt(r -> (Integer) r.get("Id")).max().orElse(0);
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            if ("modified".equals(status)) {
                String rowId = String.valueOf(modifiedRow.get("id"));
                @SuppressWarnings("unchecked")
                Map<String, Object> values = (Map<String, Object>) modifiedRow.get("values");
                for (Map<String, Object> row : source) {
                    if (String.valueOf(row.get("Id")).equals(rowId)) {
                        row.putAll(values);
                        break;
                    }
                }
            }
        }

        // 新增行：客户端把它放在第几行，回发数据的 index 就是几，服务端照着插
        // （前提是表格不分页、也没在客户端排过序，否则 index 与数据源的行序对不上）
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("newadded".equals(String.valueOf(modifiedRow.get("status")))) {
                @SuppressWarnings("unchecked")
                Map<String, Object> values = (Map<String, Object>) modifiedRow.get("values");
                Map<String, Object> newRow = new LinkedHashMap<>(values);
                newRow.put("Id", ++maxId);
                source.add(((Number) modifiedRow.get("index")).intValue(), newRow);
            }
        }
        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setTextRawHtml(new RawHtml(
                "用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>"));
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }
}
