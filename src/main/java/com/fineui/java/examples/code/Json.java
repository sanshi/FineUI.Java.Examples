package com.fineui.java.examples.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/** 示例共用：把自定义事件回带的 JSON 字符串参数解析成 {@code JsonNode}（供 {@code Page_CustomEvent} 读取字段）。 */
public final class Json {

    private static final ObjectMapper MAPPER = createMapper();

    private static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 注册 Java 8 时间模块：LocalDate/LocalDateTime 等可被序列化（ISO 字符串），
        // 否则含时间字段的对象（如 UICompareModel）encode 会抛 InvalidDefinitionException。
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    private Json() {
    }

    public static JsonNode parse(String json) {
        try {
            return MAPPER.readTree(json == null || json.isEmpty() ? "{}" : json);
        } catch (Exception e) {
            throw new RuntimeException("解析自定义事件 JSON 参数失败: " + json, e);
        }
    }

    /**
     * 把 JSON 字符串反序列化成<b>强类型</b>对象（{@link #parse(String)} 是弱类型读法：直接当 JsonNode 按名字取字段）。
     * 典型用法：{@code Json.parse(e.getArgument(), new TypeReference<List<NodeInfo>>() {})}——
     * 之后按属性访问，字段名写错在编译期就能发现。
     */
    public static <T> T parse(String json, TypeReference<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException("解析自定义事件 JSON 参数失败: " + json, e);
        }
    }

    /**
     * 把任意数据（如表格的 modifiedData/mergedData）序列化为缩进 JSON 并做 HTML 转义，供安全放进 {@code <pre>} 展示。
     * 典型用法：{@code labResult.setText("用户修改的数据：<pre>" + Json.encode(grid.getModifiedData()) + "</pre>")}。
     */
    public static String encode(Object data) {
        String json;
        try {
            json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        } catch (Exception e) {
            throw new RuntimeException("序列化 JSON 失败", e);
        }
        return json.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
