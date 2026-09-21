package com.fineui.java.examples.grid;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.Json;

/** 手工渲染行内按钮 + 自定义事件回发（路由 {@code grid/row-command-post-back-crc}）：客户端确认后触发自定义事件，服务端弹通知。 */
@FineUIPage("grid/row-command-post-back-crc")
public class RowCommandPostBackCrc extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_RowCommand".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            showNotify(String.format("你点击了第 %d 行，第 %d 列，行ID：%s，姓名：%s",
                    p.path("rowIndex").asInt() + 1,
                    p.path("columnIndex").asInt() + 1,
                    p.path("rowId").asText(),
                    p.path("rowText").asText()));
        }
    }
}
