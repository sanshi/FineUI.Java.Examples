package com.fineui.java.examples.code;

import com.fineui.java.core.controls.Grid;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * 行选择示例共用：把表格「当前选中行的数据」拼成一段可信 HTML（各数据键值的表格），供服务端按钮弹通知。
 *
 * <p>表格结构由本函数生成，数据键名与键值一律 HTML 编码后再拼——它们来自客户端回传的状态，
 * 既可能含 &amp; &lt; 把表格渲染坏，也不该当可信内容用。
 */
public final class GridSelectionMessage {

    private GridSelectionMessage() {
    }

    /** 生成「共选中了 N 行」的 HTML 表格；数据按稳定行 ID 关联，不依赖行序号。 */
    public static String howManyRowsAreSelected(Grid grid) {
        StringBuilder sb = new StringBuilder();
        List<Object[]> selectedDataKeys = grid.getSelectedDataKeys();
        if (selectedDataKeys.isEmpty()) {
            sb.append("没有选中任何一行！");
            return sb.toString();
        }

        // 表头用数据键名（各示例经 data-key-names 声明，如 Id/Name/Gender/Major）
        List<String> keyNames = new java.util.ArrayList<>();
        for (String keyName : grid.getDataKeyNames()) {
            keyNames.add(keyName);
        }

        sb.append("<p><strong>共选中了 ").append(selectedDataKeys.size()).append(" 行：</strong></p>");
        sb.append("<table class=\"result\"><tr>");
        for (String keyName : keyNames) {
            sb.append("<th>").append(HtmlUtils.htmlEscape(keyName)).append("</th>");
        }
        sb.append("</tr>");

        for (Object[] keys : selectedDataKeys) {
            sb.append("<tr>");
            for (int i = 0; i < keys.length; i++) {
                String keyName = i < keyNames.size() ? keyNames.get(i) : "";
                sb.append("<td>").append(HtmlUtils.htmlEscape(formatKeyValue(keyName, keys[i]))).append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    /** 展示层格式化：性别列的原始整数（1=男，其余=女，与 renderGender 一致）转成中文，其余键原样输出。 */
    private static String formatKeyValue(String keyName, Object value) {
        if ("Gender".equalsIgnoreCase(keyName)) {
            return "1".equals(String.valueOf(value)) ? "男" : "女";
        }
        return String.valueOf(value);
    }
}
