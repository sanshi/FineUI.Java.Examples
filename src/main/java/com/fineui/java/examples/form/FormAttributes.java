package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Image;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义属性演示页（路由 {@code form/form-attributes}）：用 tool-tip 或自定义属性（{@code data-qtip}）
 * 设置提示信息、并可在服务端改变；用自定义属性给图片设置 {@code usemap} 关联热区图。
 */
@FineUIPage("form/form-attributes")
public class FormAttributes extends PageBase {

    protected Label Label1;
    protected Label Label2;
    protected Image imgChina;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnChangeTip1_Click(Object sender, EventArgs e) {
        Label1.setToolTip("改变后的提示信息（ToolTip）");
    }

    public void btnChangeTip2_Click(Object sender, EventArgs e) {
        Label2.setAttribute("data-qtip", "改变后的提示信息（Attributes）");
    }
}
