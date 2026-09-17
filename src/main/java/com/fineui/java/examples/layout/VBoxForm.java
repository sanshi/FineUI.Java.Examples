package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * VBox（表单）演示页（路由 {@code layout/vbox-form}）：表单采用 VBox 布局竖直堆叠字段，最后的多行文本框 box-flex=1
 * 占满剩余高度；remove-fields-margin 移除字段底部外边距，使字段紧凑排列。
 */
@FineUIPage("layout/vbox-form")
public class VBoxForm extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
