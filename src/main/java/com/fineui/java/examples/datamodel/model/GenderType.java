package com.fineui.java.examples.datamodel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fineui.java.binding.Display;
import com.fineui.java.binding.EnumValue;

/** 性别枚举：控件提交使用 0/1，展示模型 JSON 时使用“女/男”。 */
public enum GenderType implements EnumValue {
    @JsonProperty("女")
    @Display(name = "女")
    WOMAN("0"),
    @JsonProperty("男")
    @Display(name = "男")
    MAN("1");

    private final String value;

    GenderType(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
