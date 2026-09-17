package com.fineui.java.examples.datamodel.model;

import com.fineui.java.binding.Display;
import com.fineui.java.binding.EnumValue;

/** 性别枚举（0/1 与显示文本映射）。 */
public enum GenderType implements EnumValue {
    @Display(name = "女")
    WOMAN("0"),
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
