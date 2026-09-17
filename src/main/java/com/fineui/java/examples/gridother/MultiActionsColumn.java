package com.fineui.java.examples.gridother;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 多操作列（路由 {@code grid-other/multi-actions-column}）：一列里放编辑图标（弹出 IFrame 窗体审批）、
 * 删除图标（确认后回发自定义事件）、以及数据加载后动态创建的按钮（点击回发自定义事件）。
 */
@FineUIPage("grid-other/multi-actions-column")
public class MultiActionsColumn extends PageBase {

    Grid Grid1;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Grid1_CustomDelete".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            showNotify(String.format("你点击了第 %d 行的删除按钮，行ID：%s，姓名：%s",
                    p.path("rowIndex").asInt() + 1, p.path("rowId").asText(), p.path("rowText").asText()));
        } else if ("Grid1_CustomEdit".equals(e.getEventName())) {
            JsonNode p = Json.parse(e.getArgument());
            showNotify(String.format("你点击了第 %d 行的编辑按钮，行ID：%s，姓名：%s",
                    p.path("rowIndex").asInt() + 1, p.path("rowId").asText(), p.path("rowText").asText()));
        }
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showAlert("触发了窗体的关闭事件！");
    }
}
