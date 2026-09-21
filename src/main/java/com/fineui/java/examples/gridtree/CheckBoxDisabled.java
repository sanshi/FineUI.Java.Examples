package com.fineui.java.examples.gridtree;

import tools.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 禁用部分复选框（路由 {@code grid-tree/check-box-disabled}）：默认勾选 basic 且禁用 basic、res 两个目录的复选框
 * （走客户端 row-data-bound 置 checked/checkboxDisabled）。「获取选中的复选框」按钮经自定义事件回发、服务端写入结果标签。
 */
@FineUIPage("grid-tree/check-box-disabled")
public class CheckBoxDisabled extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("GetCheckedRows".equals(e.getEventName())) {
            JsonNode rows = Json.parse(e.getArgument());
            if (rows.size() > 0) {
                StringBuilder sb = new StringBuilder("复选框选中的值：<ul>");
                for (JsonNode node : rows) {
                    sb.append(String.format("<li>%s（%s）</li>",
                            node.path("text").asText(), node.path("id").asText()));
                }
                sb.append("</ul>");
                labResult.setText(sb.toString());
            } else {
                labResult.setText("没有复选框被选中");
            }
        }
    }
}
