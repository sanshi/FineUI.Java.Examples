package com.fineui.java.examples.gridother;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 多操作列（下拉菜单按钮，路由 {@code grid-other/multi-actions-column-menu-button}）：编辑图标弹 IFrame 窗体审批、
 * 删除图标确认后回发、动态创建的「More」按钮带下拉菜单（各菜单项回发不同操作）。
 */
@FineUIPage("grid-other/multi-actions-column-menu-button")
public class MultiActionsColumnMenuButton extends PageBase {

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
            showNotify(String.format("你点击了第 %d 行的菜单项 %s，行ID：%s，姓名：%s",
                    p.path("rowIndex").asInt() + 1, p.path("actionName").asText(),
                    p.path("rowId").asText(), p.path("rowText").asText()));
        }
    }

    public void Window1_Close(Object sender, EventArgs e) {
        showAlert("触发了窗体的关闭事件！");
    }
}
