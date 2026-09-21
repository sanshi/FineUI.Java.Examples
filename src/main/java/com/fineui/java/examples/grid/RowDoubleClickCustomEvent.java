package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.Json;

/** 行双击回发事件（自定义回发）（路由 {@code grid/row-double-click-custom-event}）。 */
@FineUIPage("grid/row-double-click-custom-event")
public class RowDoubleClickCustomEvent extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_RowDblClick".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            showNotify(String.format("你双击了第 %d 行，行ID：%s，姓名：%s，列：%s",
                    p.path("rowIndex").asInt() + 1, p.path("rowId").asText(), p.path("rowText").asText(), p.path("columnText").asText()));
        }
    }
}
