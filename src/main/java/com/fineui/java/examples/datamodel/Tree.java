package com.fineui.java.examples.datamodel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

/**
 * 数据模型树控件 JSON（路由 {@code data-model/tree}）：树复选框勾选结果经自定义回发以 JSON 数组
 * 送到服务端，按裸 {@link JsonNode} 逐项读字段拼成结果列表展示（强类型的对照写法见
 * {@link TreeNodeInfo}）。
 *
 * <p>服务端也可以直接用 {@code Tree1.getCheckedNodes()} 拿勾选节点（见「树控件 - 复选框」示例），
 * 本页专为演示「客户端回传 JSON 参数 + 服务端解析」而存在。
 */
@FineUIPage("data-model/tree")
public class Tree extends PageBase {
    com.fineui.java.core.controls.Tree Tree1;
    Label labResult;

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if (!"GetCheckedNodes".equals(e.getEventName())) {
            return;
        }
        StringBuilder html = new StringBuilder("复选框选中的节点：<ul>");
        int count = 0;
        JsonNode nodes = Json.parse(e.getArgument());
        if (nodes.isArray()) {
            for (JsonNode node : nodes) {
                html.append("<li>").append(escape(node.path("NodeText").asText()))
                        .append("（").append(escape(node.path("NodeId").asText())).append("）</li>");
                count++;
            }
        }
        if (count == 0) {
            labResult.setText("没有复选框选中的节点");
        } else {
            labResult.setTextRawHtml(new RawHtml(html.append("</ul>").toString()));
        }
    }

    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
