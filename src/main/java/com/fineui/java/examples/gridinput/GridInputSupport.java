package com.fineui.java.examples.gridinput;

import com.fasterxml.jackson.databind.JsonNode;

/** 编辑框示例共用：把客户端收集的「每行输入值」数组渲染成结果表格 HTML（供 notify 弹出）。 */
final class GridInputSupport {

    private GridInputSupport() {
    }

    /** {@code inputs} 是 {@code [[id, 姓名, 用户输入值], ...]} 的 JSON 数组。 */
    static String buildResultTable(JsonNode inputs) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"result\"><tr><th>ID</th><th>姓名</th><th>用户输入值</th></tr>");
        if (inputs != null && inputs.isArray()) {
            for (JsonNode item : inputs) {
                sb.append("<tr><td>").append(esc(text(item, 0)))
                  .append("</td><td>").append(esc(text(item, 1)))
                  .append("</td><td>").append(esc(value(item, 2))).append("</td></tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

    private static String text(JsonNode item, int i) {
        JsonNode v = item != null && item.isArray() && item.size() > i ? item.get(i) : null;
        return v == null || v.isNull() ? "" : v.asText();
    }

    // 「用户输入值」列：数组（如复选列的多个选中值）用逗号连接，否则原样文本。
    private static String value(JsonNode item, int i) {
        JsonNode v = item != null && item.isArray() && item.size() > i ? item.get(i) : null;
        if (v == null || v.isNull()) {
            return "";
        }
        if (v.isArray()) {
            StringBuilder sb = new StringBuilder();
            for (JsonNode e : v) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(e.asText());
            }
            return sb.toString();
        }
        return v.asText();
    }

    // HTML 转义（用户输入回显进结果表格，防注入）。
    private static String esc(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
