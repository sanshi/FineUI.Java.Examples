package com.fineui.java.examples.gridtree;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.gridurl.FileTreeData;

/**
 * 树表格 · 自定义回发（路由 {@code grid-tree/grid-tree-custom-post-back}）：按钮点击时客户端收集选中行的
 * id/text/leaf/expanded，调 {@code F.customEvent('Button1Click', 结果数组)} 触发后台自定义事件。
 * 后台统一入口 {@code Page_CustomEvent} 按事件名解析 JSON 参数并回填到 Label。
 */
@FineUIPage("grid-tree/grid-tree-custom-post-back")
public class GridTreeCustomPostBack extends PageBase {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(FileTreeData.all());
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Button1Click".equals(e.getEventName())) {
            labResult.setText(String.format("选中行状态：<pre>%s</pre>", encodeJson(e.getArgument())));
        }
    }

    /** 把回带的 JSON 参数字符串格式化（缩进）后输出，便于查看。 */
    private static String encodeJson(String argument) {
        try {
            JsonNode node = MAPPER.readTree(argument == null || argument.isEmpty() ? "[]" : argument);
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (Exception ex) {
            return argument == null ? "" : argument;
        }
    }
}
