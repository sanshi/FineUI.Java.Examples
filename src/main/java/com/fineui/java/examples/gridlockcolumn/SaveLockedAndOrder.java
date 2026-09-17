package com.fineui.java.examples.gridlockcolumn;

import com.fasterxml.jackson.databind.JsonNode;
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
 * 保存列锁定状态与列顺序（路由 {@code grid-lock-column/save-locked-and-order}）：锁定/解锁列时客户端提交
 * 全部列的 {@code {ColumnID, Locked}} 序列（含顺序）到服务端会话；刷新时按会话应用 {@code order} 与 {@code locked}。
 */
@FineUIPage("grid-lock-column/save-locked-and-order")
public class SaveLockedAndOrder extends PageBase {

    private static final String SESSION_KEY = "GridLockColumn.SaveLockedAndOrder";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnLockUnlock".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            JsonNode arr = p.path("configedColumns");
            List<Map<String, Object>> saved = new ArrayList<>();
            if (arr.isArray()) {
                for (JsonNode c : arr) {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("ColumnID", c.path("ColumnID").asText());
                    m.put("Locked", c.path("Locked").asBoolean(false));
                    saved.add(m);
                }
            }
            session().setAttribute(SESSION_KEY, saved);
        }
    }

    private void loadData() {
        // 把会话保存的列顺序与锁定态应用到表格列上
        int order = 0;
        for (Map<String, Object> column : savedColumns()) {
            GridColumn found = Grid1.getColumnById((String) column.get("ColumnID"));
            if (found != null) {
                found.setColumnOrder(order);
                found.setLocked(Boolean.TRUE.equals(column.get("Locked")));
            }
            order++;
        }
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
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

    // 默认列配置（行号 + 姓名锁定，其余不锁），顺序即声明顺序。
    private static List<Map<String, Object>> defaultConfig() {
        String[] ids = {"RowNumber", "Name", "Gender", "EntranceYear", "AtSchool", "Major",
                "ShenGao", "TiZhong", "XueYaDi", "XueYaGao", "ShiLiZuo", "ShiLiYou", "Group", "LogTime"};
        List<Map<String, Object>> list = new ArrayList<>();
        for (String id : ids) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("ColumnID", id);
            m.put("Locked", "RowNumber".equals(id) || "Name".equals(id));
            list.add(m);
        }
        return list;
    }
}
