package com.fineui.java.examples.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import tools.jackson.core.type.TypeReference;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 数据模型树控件强类型（路由 {@code data-model/tree-node-info}）：与 {@link Tree} 的客户端完全相同
 * （自定义回发送出勾选节点对象数组），差异只在服务端读法——这里按<b>强类型</b>把同一份 JSON
 * 反序列化成 {@link NodeInfo} 集合，之后按属性访问、字段名写错在编译期就能发现。
 */
@FineUIPage("data-model/tree-node-info")
public class TreeNodeInfo extends PageBase {
    com.fineui.java.core.controls.Tree Tree1;
    Label labResult;

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if (!"GetCheckedNodes".equals(e.getEventName())) {
            return;
        }
        List<NodeInfo> checkedNodes = Json.parse(e.getArgument(), new TypeReference<List<NodeInfo>>() {
        });
        if (checkedNodes.isEmpty()) {
            labResult.setText("没有复选框选中的节点");
            return;
        }
        StringBuilder html = new StringBuilder("复选框选中的节点：<ul>");
        for (NodeInfo node : checkedNodes) {
            html.append("<li>").append(escape(node.nodeText())).append("（")
                    .append(escape(node.nodeId())).append("）</li>");
        }
        labResult.setTextRawHtml(new RawHtml(html.append("</ul>").toString()));
    }

    private static String escape(String text) {
        return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    /** 节点信息：JSON 键名首字母大写，故用 {@code @JsonProperty} 显式对应到 Java 惯例的字段名。 */
    record NodeInfo(@JsonProperty("NodeId") String nodeId, @JsonProperty("NodeText") String nodeText) {
    }
}
