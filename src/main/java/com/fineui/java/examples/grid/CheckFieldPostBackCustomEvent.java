package com.fineui.java.examples.grid;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.Json;

/**
 * 复选框列（自定义选中改变事件，路由 {@code grid/check-field-post-back-custom-event}）：点击复选框把行 id/文本/勾选态
 * 经自定义事件回发服务端，服务端弹出通知。
 */
@FineUIPage("grid/check-field-post-back-custom-event")
public class CheckFieldPostBackCustomEvent extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_CheckFieldChanged".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            showNotify(String.format("你点击了的行ID：%s，姓名：%s，是否在校：%s",
                    p.path("rowId").asText(), p.path("rowText").asText(),
                    p.path("isChecked").asBoolean() ? "是" : "否"));
        }
    }
}
