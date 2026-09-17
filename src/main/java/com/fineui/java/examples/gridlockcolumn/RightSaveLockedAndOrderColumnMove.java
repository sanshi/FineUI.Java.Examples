package com.fineui.java.examples.gridlockcolumn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.enums.EnumHelper;
import com.fineui.java.core.enums.LockedPosition;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存列锁定状态与列顺序（支持右侧锁定，路由 {@code grid-lock-column/right-save-locked-and-order-column-move}）：
 * 列可锁定到左侧或右侧；锁定/解锁或拖动列头时，客户端提交全部列的 {@code {ColumnID, Locked, LockedPosition}} 序列到
 * 服务端会话；刷新时按会话应用顺序、锁定态与锁定位置。
 */
@FineUIPage("grid-lock-column/right-save-locked-and-order-column-move")
public class RightSaveLockedAndOrderColumnMove extends PageBase {

    private static final String SESSION_KEY = "GridLockColumn.RightSaveLockedAndOrderColumnMove";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnLockUnlock".equals(e.getEventName())) {
            JsonNode arr = Json.parse(e.getArgument()).path("configedColumns");
            List<Map<String, Object>> saved = new ArrayList<>();
            for (JsonNode c : arr) {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("ColumnID", c.path("ColumnID").asText());
                m.put("Locked", c.path("Locked").asBoolean(false));
                m.put("LockedPosition", c.path("LockedPosition").asText(""));
                saved.add(m);
            }
            session().setAttribute(SESSION_KEY, saved);
        }
    }

    private void loadData() {
        // 把会话保存的列顺序、锁定态与锁定位置应用到表格列上
        int order = 0;
        for (Map<String, Object> column : savedColumns()) {
            GridColumn found = Grid1.getColumnById((String) column.get("ColumnID"));
            if (found != null) {
                found.setColumnOrder(order);
                found.setLocked(Boolean.TRUE.equals(column.get("Locked")));

                // LockedPosition 可能不存在
                Object pos = column.get("LockedPosition");
                if (pos != null && !((String) pos).isEmpty()) {
                    found.setLockedPosition(EnumHelper.fromName(LockedPosition.class, (String) pos, LockedPosition.Left));
                }
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

    // 默认列配置：行号 + 姓名锁定左侧，入学年份 + 分组锁定右侧，其余不锁。
    private static List<Map<String, Object>> defaultConfig() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(col("RowNumber", true, null));
        list.add(col("Name", true, null));
        list.add(col("Gender", false, null));
        list.add(col("EntranceYear", true, "right"));
        list.add(col("AtSchool", false, null));
        list.add(col("Major", false, null));
        list.add(col("ShenGao", false, null));
        list.add(col("TiZhong", false, null));
        list.add(col("XueYaDi", false, null));
        list.add(col("XueYaGao", false, null));
        list.add(col("ShiLiZuo", false, null));
        list.add(col("ShiLiYou", false, null));
        list.add(col("Group", true, "right"));
        list.add(col("LogTime", false, null));
        return list;
    }

    private static Map<String, Object> col(String columnId, boolean locked, String lockedPosition) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("ColumnID", columnId);
        m.put("Locked", locked);
        m.put("LockedPosition", lockedPosition == null ? "" : lockedPosition);
        return m;
    }
}
