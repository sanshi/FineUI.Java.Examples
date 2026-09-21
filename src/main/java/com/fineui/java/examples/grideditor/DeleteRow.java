package com.fineui.java.examples.grideditor;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
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
 * 服务器端删除行：客户端把选中行的 id 组成载荷发起自定义事件，服务端删除对应数据后重新绑定。
 * 「保存数据」按钮另处理单元格编辑的 {@code modified} 改动。
 */
@FineUIPage("grid-editor/delete-row")
public class DeleteRow extends PageBase {

    private static final String SESSION_KEY = "GridEditor.DeleteRow";
    private static final String[] COLUMNS = {"Name", "Gender", "EntranceYear", "EntranceDate", "AtSchool", "Major"};

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_DeleteRows".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            grid1DeleteRows(p.path("selectedRows"));
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    private void grid1DeleteRows(JsonNode selectedRows) {
        List<Map<String, Object>> source = sourceData();

        if (selectedRows != null && selectedRows.isArray()) {
            for (JsonNode rowId : selectedRows) {
                deleteRowById(source, rowId.asText());
            }
        }

        Grid1.setDataSource(source);
        Grid1.dataBind();

        session().setAttribute(SESSION_KEY, source);
        showNotify("删除数据成功!（表格数据已重新绑定）");
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

        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, source);
        showNotify("数据保存成功！（表格数据已重新绑定）");
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
