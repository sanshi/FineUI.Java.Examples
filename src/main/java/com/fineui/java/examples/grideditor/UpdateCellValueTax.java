package com.fineui.java.examples.grideditor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
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
 * 结束编辑（含税价与不含税价）：编辑「金额（不含税）」「金额（含税）」「税额」中任一列，客户端脚本按 3% 征收率
 * 联动算出另两列（编辑期间用 {@code F.noEvent} 防止循环触发）。表格初始为空，用户逐行新增。
 * 「保存数据」通过自定义事件把合并数据（{@code getMergedData}）回带服务端，重建数据源、重新绑定并展示 JSON。
 */
@FineUIPage("grid-editor/update-cell-value-tax")
public class UpdateCellValueTax extends PageBase {

    private static final String SESSION_KEY = "GridEditor.UpdateCellValueTax";

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Submit_Click".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            submitClick(p.path("mergedData"));
        }
    }

    private void loadData() {
        Grid1.setDataSource(sourceData());
        Grid1.dataBind();
    }

    private void submitClick(JsonNode mergedData) {
        int rowIndex = 0;
        List<Map<String, Object>> newTable = new ArrayList<>();
        if (mergedData != null && mergedData.isArray()) {
            for (JsonNode mergedRow : mergedData) {
                JsonNode values = mergedRow.path("values");

                Map<String, Object> newRow = new LinkedHashMap<>();
                newRow.put("Id", rowIndex);   // 实际项目中请使用数据库中的自增长主键，无需设置此列的值
                newRow.put("Name", values.path("Name").asText(null));
                newRow.put("Price", numberOrNull(values.path("Price")));
                newRow.put("PriceWithTax", numberOrNull(values.path("PriceWithTax")));
                newRow.put("Tax", numberOrNull(values.path("Tax")));
                newRow.put("TaxPercent", numberOrNull(values.path("TaxPercent")));
                newTable.add(newRow);

                rowIndex++;
            }
        }

        Grid1.setDataSource(newTable);
        Grid1.dataBind();

        session().setAttribute(SESSION_KEY, newTable);

        labResult.setText("用户修改的数据：<pre>" + Json.encode(mergedData) + "</pre>");
        showNotify("数据保存成功！（表格数据已重新绑定）");
    }

    private static Double numberOrNull(JsonNode node) {
        return node == null || node.isMissingNode() || node.isNull() ? null : node.asDouble();
    }

    /**
     * 服务端会话缓存的可变数据源：初始为空（列结构由表格列定义提供），用户在客户端逐行新增。
     * 特别注意：真实开发中不要在会话放大量数据，否则严重影响服务器性能。
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> empty = new ArrayList<>();
            session().setAttribute(SESSION_KEY, empty);
            return empty;
        }
        return (List<Map<String, Object>>) cached;
    }
}
