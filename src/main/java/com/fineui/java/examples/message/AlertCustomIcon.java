package com.fineui.java.examples.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.core.enums.Target;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义对话框图标演示页（路由 {@code message/alert-custom-icon}）：三种自定义图标来源——
 * ① 内置图标 {@code Icon.Book}（渲染 {@code <img src=.../book.png>}）；
 * ② 自定义图片地址（渲染 {@code <img>}）；③ 字体图标 {@code IconFont._Car}（渲染 {@code <i class="f-iconfont">}）。
 */
@FineUIPage("message/alert-custom-icon")
public class AlertCustomIcon extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnHello_Click(Object sender, EventArgs e) {
        showAlert("你好 FineUI！", null, MessageBoxIcon.None, Target.Self,
                IconHelper.namedIconUrl("Book"), null);
    }

    public void btnHello2_Click(Object sender, EventArgs e) {
        showAlert("你好 FineUI！", null, MessageBoxIcon.None, Target.Top,
                "/res/images/success.png", null);
    }

    public void btnHello3_Click(Object sender, EventArgs e) {
        showAlert("你好 FineUI！", null, MessageBoxIcon.None, Target.Top,
                null, IconFont._Car.getName());
    }
}
