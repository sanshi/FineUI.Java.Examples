package com.fineui.java.examples.grideditor;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 数字编辑框（初始为空）：年份及各项费用用数字编辑框录入，费用可以为空（null），也可将现有数字更新为空。
 * 保存时遍历用户修改的数据把改动落到服务端会话缓存的数据源、重新绑定，并把修改的 JSON 展示到标签。
 * 对于数值列，客户端传回空字符串表示"清空"，此时写回 null。
 */
@FineUIPage("grid-editor/number-box-empty")
public class NumberBoxEmpty extends PageBase {

    private static final String SESSION_KEY = "GridEditor.NumberBoxEmpty";

    // 全部为数值列：空字符串表示清空 → 写 null。
    private static final String[] NUMERIC_COLUMNS = {"Year", "XueFei", "ZhusuFei", "HuoshiFei", "ShubenFei"};

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
                updateDataRow(modifiedRow, rowId, source);
            }
        }

        Grid1.setDataSource(source);
        Grid1.dataBind();

        labResult.setText("用户修改的数据：<pre>" + Json.encode(Grid1.getModifiedData()) + "</pre>");

        session().setAttribute(SESSION_KEY, source);
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    @SuppressWarnings("unchecked")
    private void updateDataRow(Map<String, Object> modifiedRow, String rowId, List<Map<String, Object>> source) {
        Object valuesObj = modifiedRow.get("values");
        if (!(valuesObj instanceof Map)) {
            return;
        }
        Map<String, Object> values = (Map<String, Object>) valuesObj;
        Map<String, Object> rowData = findRowById(source, rowId);
        if (rowData == null) {
            return;
        }
        for (String column : NUMERIC_COLUMNS) {
            if (values.containsKey(column)) {
                Object value = values.get(column);
                // 客户端值为空字符串（数值列）时，写回 null 表示"空"
                if (value instanceof String && ((String) value).isEmpty()) {
                    rowData.put(column, null);
                } else {
                    rowData.put(column, value);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : NumberBoxEmptyGridData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
