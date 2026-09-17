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
import com.fineui.java.examples.code.StudentGridData2;

/** 列拖拽排序（路由 {@code grid-move/column-move}）：拖动列头后把列顺序保存到服务端会话，刷新页面时按会话回填列顺序。 */
@FineUIPage("grid-move/column-move")
public class ColumnMove extends PageBase {

    private static final String SESSION_KEY = "GridMove.ColumnMove";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnMove".equals(e.getEventName())) {
            // 保存客户端上报的列顺序（columnId 数组，下标即顺序）
            session().setAttribute(SESSION_KEY, e.getArgument());
        }
    }

    private void loadData() {
        Object saved = session().getAttribute(SESSION_KEY);
        if (saved instanceof String json && !json.isEmpty()) {
            // 将会话保存的列顺序应用到表格列上
            JsonNode columnIds = Json.parse(json).path("columnIds");
            int order = 0;
            for (JsonNode columnId : columnIds) {
                GridColumn found = Grid1.getColumnById(columnId.asText());
                if (found != null) {
                    found.setColumnOrder(order);
                }
                order++;
            }
        }
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    // 清空数据：数据源置空后重新绑定，客户端清空表格并显示空占位行。
    public void btnClearData_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(null);
        Grid1.dataBind();
    }

    // 重新绑定数据：换用 22 行的 StudentGridData2 数据重新绑定。
    public void btnRebindData_Click(Object sender, EventArgs e) {
        Grid1.setDataSource(StudentGridData2.rows());
        Grid1.dataBind();
    }
}
