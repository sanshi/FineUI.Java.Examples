package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增行（选中新增的行）：新增一行后使某一列自动进入编辑状态、并选中该新增行。
 * 保存时遍历用户修改的数据（{@link Grid#getModifiedData()}）处理 {@code modified}/{@code newadded}，
 * 落到会话缓存的数据源、重新绑定，并把修改的 JSON 展示到标签。
 */
@FineUIPage("grid-editor/new-select-row")
public class NewSelectRow extends PageBase {

    private static final String SESSION_KEY = "GridEditor.NewSelectRow";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<Map<String, Object>> source = sourceData();

        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            String status = String.valueOf(modifiedRow.get("status"));
            String rowId = String.valueOf(modifiedRow.get("id"));
            if ("modified".equals(status)) {
                updateDataRow(modifiedRow, rowId, source, COLUMNS);
            }
        }

        // 新增行：客户端把它放在第几行，回发数据的 index 就是几，服务端照着插
        // （前提是表格不分页、也没在客户端排过序，否则 index 与数据源的行序对不上）
        for (Map<String, Object> modifiedRow : Grid1.getModifiedData()) {
            if ("newadded".equals(String.valueOf(modifiedRow.get("status")))) {
                source.add(((Number) modifiedRow.get("index")).intValue(), createNewData(modifiedRow, source));
            }
        }

        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, source);
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    private Map<String, Object> createNewData(Map<String, Object> modifiedRow, List<Map<String, Object>> source) {
        Map<String, Object> rowData = new LinkedHashMap<>();
        // 设置行ID（模拟数据库的自增长列）
        rowData.put("Id", getNextRowId(source));
        updateDataRow(modifiedRow, rowData, COLUMNS);
        return rowData;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : StudentGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
