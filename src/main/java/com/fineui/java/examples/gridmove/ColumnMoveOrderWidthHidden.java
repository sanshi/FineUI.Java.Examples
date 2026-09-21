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

/**
 * 列拖拽排序（保存列顺序/宽度/隐藏，路由 {@code grid-move/column-move-order-width-hidden}）：
 * 用户拖动列头、拉伸列宽、经列头菜单隐藏列后，「保存数据」把各列的顺序/宽度/隐藏态存进服务端会话；
 * 刷新页面时按会话回填 {@code columnOrder}/{@code width}/{@code hidden}，从而保持列布局。
 */
@FineUIPage("grid-move/column-move-order-width-hidden")
public class ColumnMoveOrderWidthHidden extends PageBase {

    private static final String SESSION_KEY = "GridMove.ColumnMoveOrderWidthHidden";

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    // 会话为空时的默认列布局：各列固定宽度，Major 列 flex:1（吃剩余宽度）——用于演示 flex 优先于 width 恢复。
    private static final String DEFAULT_LAYOUT_JSON = "{\"columnIds\":["
            + "{\"columnId\":\"RowNumber\",\"width\":30},"
            + "{\"columnId\":\"Name\",\"width\":100},"
            + "{\"columnId\":\"Gender\",\"width\":80},"
            + "{\"columnId\":\"EntranceYear\",\"width\":80},"
            + "{\"columnId\":\"AtSchool\",\"width\":80},"
            + "{\"columnId\":\"Major\",\"flex\":1},"
            + "{\"columnId\":\"LogTime\",\"width\":100}]}";

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_ColumnMove".equals(e.getEventName())) {
            // 保存客户端上报的列布局（顺序即数组下标 + 各自的 hidden/width/flex）
            session().setAttribute(SESSION_KEY, e.getArgument());
        }
    }

    private void loadData() {
        Object saved = session().getAttribute(SESSION_KEY);
        String json = saved instanceof String s && !s.isEmpty() ? s : DEFAULT_LAYOUT_JSON;

        JsonNode columns = Json.parse(json).path("columnIds");
        int order = 0;
        for (JsonNode column : columns) {
            GridColumn found = Grid1.getColumnById(column.path("columnId").asText());
            if (found != null) {
                found.setColumnOrder(order);
                if (column.path("hidden").asBoolean(false)) {
                    found.setHidden(true);
                }
                // flex 属性可能不存在；存在则优先于 width（对齐 F.js 的 HBox 布局比例语义）
                if (column.has("flex")) {
                    found.setBoxFlex(column.path("flex").asInt());
                } else if (column.has("width")) {
                    found.setWidth(column.path("width").asInt());
                }
            }
            order++;
        }
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }
}
