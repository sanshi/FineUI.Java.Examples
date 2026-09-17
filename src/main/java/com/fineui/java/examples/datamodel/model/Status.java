package com.fineui.java.examples.datamodel.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fineui.java.binding.Display;

import java.lang.reflect.Field;

/** 学生状态枚举：数值（Grid 列直接渲染，经 {@link JsonValue} 序列化为底层数值）+ {@link Display} 显示名（反射派生）。 */
public enum Status {
    @Display(name = "优秀")
    Excellent(1),
    @Display(name = "良好")
    Good(2),
    @Display(name = "补考")
    MakeUp(3),
    @Display(name = "重修")
    Retake(4);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    /** 枚举底层数值（渲染为 1/2/3/4）；{@link JsonValue} 使 Jackson 序列化该枚举时输出数值而非枚举名。 */
    @JsonValue
    public int getValue() {
        return value;
    }

    /** 反射 {@link Display} 注解取显示名；未标注时回落枚举名。 */
    public String displayName() {
        try {
            Field field = Status.class.getField(name());
            Display display = field.getAnnotation(Display.class);
            return display != null && !display.name().isBlank() ? display.name() : name();
        } catch (NoSuchFieldException e) {
            return name();
        }
    }
}
