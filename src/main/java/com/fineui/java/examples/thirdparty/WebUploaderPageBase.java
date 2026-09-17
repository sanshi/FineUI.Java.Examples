package com.fineui.java.examples.thirdparty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

/** WebUploader 示例页共享的会话和删除处理。 */
abstract class WebUploaderPageBase extends PageBase {

    /** DeleteRow 事件传递的是原始 row id，不是 JSON。 */
    protected void deleteRow(String owner, String rowId) {
        WebUploaderStore.delete(session(), owner, rowId);
    }

    /** DeleteRows 事件传递的是由客户端数组序列化得到的 JSON。 */
    protected void deleteRows(String owner, String argument) {
        JsonNode rows = Json.parse(argument);
        for (JsonNode row : rows) {
            WebUploaderStore.delete(session(), owner, rowId(row));
        }
    }

    private static String rowId(JsonNode row) {
        return row.isTextual() ? row.asText() : row.path("id").asText();
    }
}
