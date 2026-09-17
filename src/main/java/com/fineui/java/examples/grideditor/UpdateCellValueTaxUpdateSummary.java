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
 * 结束编辑（含税价与不含税价，合计行）：在含税/不含税联动算价的基础上，客户端脚本在编辑后与数据加载后
 * 重算底部合计行（金额/含税金额/税额各列求和）。表格初始为空，用户逐行新增。
 * 「保存数据」通过自定义事件把合并数据（{@code getMergedData}）回带服务端，重建数据源、重新绑定并展示 JSON。
 */
@FineUIPage("grid-editor/update-cell-value-tax-update-summary")
public class UpdateCellValueTaxUpdateSummary extends PageBase {

    private static final String SESSION_KEY = "GridEditor.UpdateCellValueTaxUpdateSummary";

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
