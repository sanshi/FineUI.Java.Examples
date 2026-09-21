package com.fineui.java.examples.gridlockcolumn;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.RowNumberField;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存列锁定状态（路由 {@code grid-lock-column/save-locked}）：用户经列头菜单锁定/解锁列时，客户端
 * {@code columnlock}/{@code columnunlock} 事件触发自定义回发，把该列锁定态存进服务端会话；刷新页面时按
 * 会话里的锁定列回填 {@code locked}，从而保持锁定状态。
 */
@FineUIPage("grid-lock-column/save-locked")
public class SaveLocked extends PageBase {

    private static final String SESSION_KEY = "GridLockColumn.SaveLocked";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnLockUnlock".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            String type = p.path("type").asText();
            String columnId = p.path("columnId").asText();
            List<String> locked = lockedColumns();
            if ("lock".equals(type)) {
                if (!locked.contains(columnId)) {
                    locked.add(columnId);
                }
            } else if ("unlock".equals(type)) {
                locked.remove(columnId);
            }
            session().setAttribute(SESSION_KEY, locked);
        }
    }

    private void loadData() {
        // 按会话里保存的锁定列回填 locked（行号列由 F.js 随锁定自动固定左侧，无需显式设）
        for (String id : lockedColumns()) {
            GridColumn column = Grid1.getColumnById(id);
            if (column != null && !(column instanceof RowNumberField)) {
                column.setLocked(true);
            }
        }
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    @SuppressWarnings("unchecked")
    private List<String> lockedColumns() {
        Object v = session().getAttribute(SESSION_KEY);
        if (v == null) {
            List<String> def = new ArrayList<>(List.of("RowNumber", "Name"));   // 默认锁定行号 + 姓名
            session().setAttribute(SESSION_KEY, def);
            return def;
        }
        return (List<String>) v;
    }
}
