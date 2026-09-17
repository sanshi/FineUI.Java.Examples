package com.fineui.java.examples.gridmove;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 列拖拽排序（列锁定，路由 {@code grid-move/column-move-lock-column}）：用户拖动列头调整顺序、
 * 或经列头菜单锁定/解锁列后，把当前列顺序 + 锁定列集合存进服务端会话；刷新页面时按会话回填
 * 各列的 {@code columnOrder}/{@code locked}，从而保持列顺序和锁定状态。
 */
@FineUIPage("grid-move/column-move-lock-column")
public class ColumnMoveLockColumn extends PageBase {

    private static final String SESSION_KEY_COLUMN_IDS = "GridMove.ColumnMoveLockColumn.ColumnIds";
    private static final String SESSION_KEY_LOCKED_COLUMN_IDS = "GridMove.ColumnMoveLockColumn.LockedColumnIds";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnMoveOrLock".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());

            List<String> columnIds = new ArrayList<>();
            for (JsonNode id : p.path("columnIds")) {
                columnIds.add(id.asText());
            }

            List<String> lockedColumnIds = new ArrayList<>();
            for (JsonNode id : p.path("lockedColumnIds")) {
                lockedColumnIds.add(id.asText());
            }

            // 模拟操作数据库中的数据
            session().setAttribute(SESSION_KEY_COLUMN_IDS, columnIds);
            session().setAttribute(SESSION_KEY_LOCKED_COLUMN_IDS, lockedColumnIds);
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        List<String> savedColumnIds = (List<String>) session().getAttribute(SESSION_KEY_COLUMN_IDS);
        List<String> savedLockedColumnIds = (List<String>) session().getAttribute(SESSION_KEY_LOCKED_COLUMN_IDS);
        if (savedColumnIds != null && !savedColumnIds.isEmpty()) {
            Set<String> lockedSet = savedLockedColumnIds == null ? Set.of() : new HashSet<>(savedLockedColumnIds);

            // 将会话保存的值应用到表格列上
            for (GridColumn column : Grid1.getColumns()) {
                String columnId = column.getColumnId();

                // 列锁定
                column.setLocked(lockedSet.contains(columnId));

                // 列顺序
                int order = savedColumnIds.indexOf(columnId);
                if (order >= 0) {
                    column.setColumnOrder(order);
                }
            }
        }

        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }
}
