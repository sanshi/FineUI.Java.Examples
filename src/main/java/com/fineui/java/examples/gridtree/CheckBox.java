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
 * 树表格 · 复选框（路由 {@code grid-tree/check-box}）：开启行复选框（tree-check-box）。
 * 「获取选中的复选框」按钮由客户端读取勾选行、经自定义事件回发，服务端解析后写入结果标签。
 */
@FineUIPage("grid-tree/check-box")
public class CheckBox extends PageBase {

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
