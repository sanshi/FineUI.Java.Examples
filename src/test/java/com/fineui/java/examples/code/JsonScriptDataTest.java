package com.fineui.java.examples.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class JsonScriptDataTest {

    @Test
    void scriptDataKeepsOriginalValueWithoutEndingTheTag() {
        String original = "</script><script>alert(1)</script>&";

        String encoded = Json.encodeForScriptData(Map.of("name", original));

        // 数据仍能被 JSON.parse 还原，但 HTML 解析器不会把其中的结束标签当作真正的标签。
        assertFalse(encoded.contains("</script>"));
        assertEquals(original, Json.parse(encoded).get("name").asText());
    }
}
