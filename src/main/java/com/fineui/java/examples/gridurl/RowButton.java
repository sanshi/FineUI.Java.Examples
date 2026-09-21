package com.fineui.java.examples.gridurl;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 行内按钮（路由 {@code grid-data-url/row-button}）：末列由客户端 {@code renderActions} 渲染
 * 编辑/删除按钮，点击后经 {@code F.customEvent} 触发服务端自定义事件，服务端读取行信息弹通知。
 */
@FineUIPage("grid-data-url/row-button")
public class RowButton extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {

        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_CustomEvent".equals(e.getEventName())) {
            JsonNode param = Json.parse(e.getArgument());

            String eventType = param.path("eventType").asText();
            String eventTypeStr = "";
            if ("edit".equals(eventType)) {
                eventTypeStr = "编辑";
            } else if ("delete".equals(eventType)) {
                eventTypeStr = "删除";
            }

            showNotify(String.format("你点击了第 %d 行的 %s 按钮，行ID：%s，姓名：%s",
                    param.path("rowIndex").asInt() + 1,
                    eventTypeStr,
                    param.path("rowId").asText(),
                    param.path("rowText").asText()));
        }
    }
}
