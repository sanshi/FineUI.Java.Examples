package com.fineui.java.examples.gridmove;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 行拖拽列配置（路由 {@code grid-move/row-move-column-config}）：弹出「列配置」窗体，用一个配置表格上下移动、显隐、
 * 改标题来调整主表格的列布局；每次改动通过自定义回发把列配置（顺序/显隐/标题）存进服务端会话，刷新页面时按会话回填。
 */
@FineUIPage("grid-move/row-move-column-config")
public class RowMoveColumnConfig extends PageBase {

    private static final String SESSION_KEY = "GridMove.RowMoveColumnConfig";

    // 默认列配置（顺序即声明顺序；RowNumber 默认隐藏）。
    private static List<Map<String, Object>> defaultConfig() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(col("RowNumber", true, ""));
        list.add(col("Name", false, "姓名"));
        list.add(col("Gender", false, "性别"));
        list.add(col("EntranceYear", false, "入学年份"));
        list.add(col("AtSchool", false, "是否在校"));
        list.add(col("Major", false, "所学专业"));
        list.add(col("LogTime", false, "注册日期"));
        return list;
    }

    private static Map<String, Object> col(String columnId, boolean hidden, String headerText) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("ColumnID", columnId);
        m.put("Hidden", hidden);
        m.put("HeaderText", headerText);
        return m;
    }

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        List<Map<String, Object>> savedColumns = savedColumns();
        int order = 0;
        for (Map<String, Object> column : savedColumns) {
            GridColumn found = Grid1.getColumnById((String) column.get("ColumnID"));
            if (found != null) {
                found.setColumnOrder(order);
                // 约定：Hidden 属性一定存在
                found.setHidden(Boolean.TRUE.equals(column.get("Hidden")));
                // 约定：HeaderText 属性一定存在
                found.setHeaderText(String.valueOf(column.get("HeaderText")));
            }
            order++;
        }
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("GridConfig_Change".equals(e.getEventName())) {
            JsonNode arr = Json.parse(e.getArgument()).path("configedColumns");
            List<Map<String, Object>> saved = new ArrayList<>();
            for (JsonNode c : arr) {
                saved.add(col(c.path("ColumnID").asText(), c.path("Hidden").asBoolean(false),
                        c.path("HeaderText").asText("")));
            }
            session().setAttribute(SESSION_KEY, saved);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> savedColumns() {
        Object v = session().getAttribute(SESSION_KEY);
        if (v == null) {
            List<Map<String, Object>> def = defaultConfig();
            session().setAttribute(SESSION_KEY, def);
            return def;
        }
        return (List<Map<String, Object>>) v;
    }
}
