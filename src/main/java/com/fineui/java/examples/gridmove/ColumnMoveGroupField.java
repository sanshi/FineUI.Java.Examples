package com.fineui.java.examples.gridmove;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.GroupField;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 多表头列拖拽排序（路由 {@code grid-move/column-move-group-field}）：表格带多级分组表头，拖动列头调整列顺序后，
 * 客户端把带嵌套结构的列顺序（每个分组含 {@code columns} 子数组）提交到服务端会话；刷新页面时按会话逐级回填
 * {@code order}，从而保持列顺序。
 */
@FineUIPage("grid-move/column-move-group-field")
public class ColumnMoveGroupField extends PageBase {

    private static final String SESSION_KEY = "GridMove.ColumnMoveGroupField";

    // 默认列顺序（嵌套结构：分组列带 columns 子数组）。
    private static final String DEFAULT_COLUMN_IDS =
            "{\"columnIds\":[{\"columnId\":\"Year\"},{\"columnId\":\"anhui\",\"columns\":[{\"columnId\":\"hefei\","
            + "\"columns\":[{\"columnId\":\"AHData1\"},{\"columnId\":\"AHData2\"}]}]},{\"columnId\":\"henan\","
            + "\"columns\":[{\"columnId\":\"zhumadian\",\"columns\":[{\"columnId\":\"HZData1\"},{\"columnId\":\"HZData2\"}]},"
            + "{\"columnId\":\"luohe\",\"columns\":[{\"columnId\":\"HLData1\"},{\"columnId\":\"HLData2\"}]}]},"
            + "{\"columnId\":\"LogTime\"}]}";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnMove".equals(e.getEventName())) {
            // 模拟操作数据库中的数据：保存客户端上报的（嵌套）列顺序
            session().setAttribute(SESSION_KEY, e.getArgument());
        }
    }

    private void loadData() {
        JsonNode savedColumns = savedColumns().path("columnIds");
        if (savedColumns.isArray() && savedColumns.size() > 0) {
            // 将会话保存的列顺序应用到表格列上
            applyOrders(savedColumns);
        }
        Grid1.setDataSource(getDataTable());
        Grid1.dataBind();
    }

    // 逐级回填列顺序：每一层的 order 从 0 开始，按层递归重排。
    private void applyOrders(JsonNode savedColumns) {
        int order = 0;
        for (JsonNode column : savedColumns) {
            GridColumn found = findColumn(Grid1.getColumns(), column.path("columnId").asText());
            if (found != null) {
                found.setColumnOrder(order);
            }
            JsonNode children = column.get("columns");
            if (children != null) {
                applyOrders(children);
            }
            order++;
        }
    }

    // 递归查找列（含分组表头的嵌套子列）。
    private GridColumn findColumn(List<GridColumn> cols, String columnId) {
        for (GridColumn c : cols) {
            if (columnId.equals(c.getColumnId())) {
                return c;
            }
            if (c instanceof GroupField gf) {
                GridColumn found = findColumn(gf.getColumns(), columnId);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    private JsonNode savedColumns() {
        Object v = session().getAttribute(SESSION_KEY);
        if (!(v instanceof String json) || json.isEmpty()) {
            session().setAttribute(SESSION_KEY, DEFAULT_COLUMN_IDS);
            return Json.parse(DEFAULT_COLUMN_IDS);
        }
        return Json.parse(json);
    }

    // 模拟数据库数据（10 行统计数据）。
    private List<Map<String, Object>> getDataTable() {
        List<Map<String, Object>> list = new ArrayList<>();
        Random rd = new Random();
        for (int i = 0; i < 10; i++) {
            int year = 2000 + i;
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", 100 + i);
            row.put("Guid", UUID.randomUUID().toString());
            row.put("Year", year);
            row.put("HZData1", 1000 + rd.nextInt(9000));
            row.put("HZData2", 1000 + rd.nextInt(9000));
            row.put("HLData1", 1000 + rd.nextInt(9000));
            row.put("HLData2", 1000 + rd.nextInt(9000));
            row.put("AHData1", 1000 + rd.nextInt(9000));
            row.put("AHData2", 1000 + rd.nextInt(9000));
            row.put("LogTime", LocalDate.of(year, 9, 1));
            list.add(row);
        }
        return list;
    }
}
