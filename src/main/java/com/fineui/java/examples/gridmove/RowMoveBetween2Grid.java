package com.fineui.java.examples.gridmove;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 两个表格间移动行（路由 {@code grid-move/row-move-between2-grid}）：左表为数据源、右表为已选择；中间按钮把行在
 * 两表间搬移（全在客户端完成）。底部「选择了哪些用户」按钮走自定义回发，把右表当前行拼成列表用通知弹出。
 */
@FineUIPage("grid-move/row-move-between2-grid")
public class RowMoveBetween2Grid extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("CheckSelected_Click".equals(e.getEventName())) {
            checkSelectedClick(Json.parse(e.getArgument()).path("columnNames"));
        }
    }

    private void checkSelectedClick(JsonNode columnNames) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        for (JsonNode item : columnNames) {
            sb.append("<li>ID:").append(item.path("id").asText())
                    .append(" Name:").append(item.path("name").asText()).append("</li>");
        }
        sb.append("</ul>");

        showNotifyRaw("已选择列表：" + sb);
    }
}
