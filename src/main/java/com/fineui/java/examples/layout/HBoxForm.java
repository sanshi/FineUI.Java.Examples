package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * HBox（横向表单）演示页（路由 {@code layout/hbox-form}）：用 SimpleForm 的 HBox 与 Column 两种布局把日期区间字段横向排列；
 * 结束日期用 compare-control/compare-operator 校验须大于开始日期；「重置」按钮用客户端脚本重置所在表单。
 */
@FineUIPage("layout/hbox-form")
public class HBoxForm extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
