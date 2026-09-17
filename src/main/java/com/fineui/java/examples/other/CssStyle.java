package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * CssClass 与 CssStyle 属性演示页面模型类（路由 {@code other/css-style}）：
 * Button1 用随机 RGB 内联样式改 Label1 的 CssStyle；Button3 清空 Label1 的 style 属性；
 * Button2 在 red/green 之间切换 Label2 的 CssClass；Button4 清空 Label2 的 CssClass。
 */
@FineUIPage("other/css-style")
public class CssStyle extends PageBase {

    Label Label1;
    Label Label2;

    public void Page_Load(Object sender, EventArgs e) {
    }

    private final java.util.Random rd = new java.util.Random();

    /** 通过 CssStyle 修改文本的样式：随机 RGB 颜色 + 加粗 + 放大。 */
    public void Button1_Click(Object sender, EventArgs e) {
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);
        Label1.setCssStyle("font-size:1.5em;font-weight:bold;color:rgb(" + r + "," + g + "," + b + ");");
    }

    /** 清空 CssStyle 设置的样式（直接设空串，桥接层更新元素 style 属性）。 */
    public void Button3_Click(Object sender, EventArgs e) {
        Label1.setCssStyle("");
    }

    /** 通过 CssClass 修改文本的样式：red/green 之间切换。 */
    public void Button2_Click(Object sender, EventArgs e) {
        Label2.setCssClass("red".equals(Label2.getCssClass()) ? "green" : "red");
    }

    /** 清空 CssClass 设置的样式。 */
    public void Button4_Click(Object sender, EventArgs e) {
        Label2.setCssClass("");
    }
}
