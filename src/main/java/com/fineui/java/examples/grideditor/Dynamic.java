package com.fineui.java.examples.grideditor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.NumberBox;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.core.controls.RowNumberField;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.enums.FieldType;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态创建可编辑列：所有列（收费项目、各年份费用、合计）都在服务端用代码构建。年份费用列可编辑，
 * 修改第一年（{@code _minYear}）的值会级联更新其余年份并重算合计（客户端脚本处理）。
 * 保存时把各年份的修改累积回该行的 {@code YearData}（JSON 字符串）并重新绑定。
 */
@FineUIPage("grid-editor/dynamic")
public class Dynamic extends PageBase {

    private static final String SESSION_KEY = "GridEditor.Dynamic";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final int minYear = 2014;
    private final int maxYear = 2017;

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            buildColumns();
            Grid1.setDataSource(sourceData());
            Grid1.dataBind();
            // 年份区间常量提供给客户端脚本使用（见 dynamic.html 内联 window._MINYEAR / _MAXYEAR）
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        // 页面每次请求都是全新的对象，回发时需重建列，dataBind 才能按列解析出要序列化的字段
        buildColumns();

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

    // 程序化建列：序号列 + 收费项目 + 隐藏的 YearData 原始数据列 + 各年份可编辑列 + 合计列
    private void buildColumns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());

        // 收费项目
        RenderField nameField = new RenderField();
        nameField.setWidth(100);
        nameField.setColumnId("Name");
        nameField.setDataField("Name");
        nameField.setHeaderText("收费项目");
        nameField.setExpandUnusedSpace(true);
        columns.add(nameField);

        // 年份原始数据（隐藏列）：各年份费用打包成 JSON 字符串存于此
        RenderField yearDataField = new RenderField();
        yearDataField.setColumnId("YearData");
        yearDataField.setDataField("YearData");
        yearDataField.setHeaderText("年份数据（隐藏）");
        yearDataField.setHidden(true);
        columns.add(yearDataField);

        // 年份列（可编辑数字框）：无绑定字段，值由客户端 rowDataBound 从 YearData 展开
        for (int i = minYear; i <= maxYear; i++) {
            RenderField rf = new RenderField();
            rf.setWidth(120);
            rf.setHeaderText(i + "年");
            rf.setFieldType(FieldType.Int);
            rf.setColumnId("Year_" + i);

            NumberBox nb = new NumberBox();
            nb.setNoNegative(true);
            nb.setId("Year_" + i + "_Editor");
            nb.setRequired(true);
            rf.setEditor(nb);

            columns.add(rf);
        }

        // 合计列（无绑定字段，值由客户端脚本累加）
        RenderField totalField = new RenderField();
        totalField.setWidth(120);
        totalField.setHeaderText("合计");
        totalField.setFieldType(FieldType.Int);
        totalField.setColumnId("TotalFee");
        columns.add(totalField);

        Grid1.getColumns().clear();
        for (GridColumn column : columns) {
            Grid1.addColumn(column);
        }
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

        // 读出该行已存的 YearData（JSON），无则空
        Map<String, Object> yearData = new LinkedHashMap<>();
        Object ydObj = rowData.get("YearData");
        String ydStr = ydObj == null ? "" : String.valueOf(ydObj);
        if (!ydStr.isEmpty()) {
            JsonNode node = Json.parse(ydStr);
            Iterator<String> names = node.fieldNames();
            while (names.hasNext()) {
                String n = names.next();
                yearData.put(n, node.get(n));
            }
        }

        // 用修改的年份值覆盖
        for (int i = minYear; i <= maxYear; i++) {
            String key = "Year_" + i;
            if (values.containsKey(key)) {
                yearData.put(String.valueOf(i), values.get(key));
            }
        }

        rowData.put("YearData", encodeCompact(yearData));
    }

    private static String encodeCompact(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (Exception e) {
            throw new RuntimeException("序列化 YearData 失败", e);
        }
    }

    /**
     * 服务端会话缓存的可变数据源：首次从演示数据深拷贝一份放进会话，之后返回该份（保存时原地修改）。
     * 特别注意：真实开发中不要在会话放大量数据，否则严重影响服务器性能。
     */
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> sourceData() {
        Object cached = session().getAttribute(SESSION_KEY);
        if (cached == null) {
            List<Map<String, Object>> copy = new ArrayList<>();
            for (Map<String, Object> row : DynamicFeeData.rows()) {
                copy.add(new LinkedHashMap<>(row));
            }
            session().setAttribute(SESSION_KEY, copy);
            return copy;
        }
        return (List<Map<String, Object>>) cached;
    }
}
