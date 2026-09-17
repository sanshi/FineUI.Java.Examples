package com.fineui.java.examples.code;

import com.fineui.java.examples.multilang.model.UICompareModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** 冒烟：Json.encode 能序列化含 LocalDate 的模型（UICompareAnnotation 提交成功路径依赖）。 */
class JsonLocalDateSmokeTest {

    @Test
    void encodeModelWithLocalDate() {
        UICompareModel model = new UICompareModel();
        model.setStartDate(LocalDate.of(2024, 1, 1));
        model.setNumber1(30);
        String json = Json.encode(model);
        assertTrue(json.contains("2024-01-01"), "LocalDate 应序列化为 ISO 字符串: " + json);
    }
}
