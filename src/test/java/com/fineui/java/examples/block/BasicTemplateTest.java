package com.fineui.java.examples.block;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** 回归：Block 示例必须使用规范的单属性可信 HTML 标签；转换协议由 core 集成测试覆盖。 */
class BasicTemplateTest {

    @Test
    void rawLabelsUseTheCanonicalTemplateAttribute() throws IOException {
        try (var stream = getClass().getResourceAsStream("/templates/block/basic.html")) {
            String template = new String(stream.readAllBytes(), StandardCharsets.UTF_8);

            assertTrue(template.contains("<f:label id=\"Label1\" text-raw-html=\"BlockMD=6<br/>BlockLG=4\""));
            assertFalse(template.contains("textRawHtml"));
        }
    }
}
