package com.fineui.java.examples.other;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义回发动画演示页面模型类（路由 {@code other/form-ajax-complete}）：表单校验通过后点击提交，
 * 页面脚本显示表单自身的加载遮罩（{@code form1.showLoading()}）、用 {@code F.doPostBack} 发自定义回发
 * {@code Form1_Submit}（不显示页面级遮罩），服务端休眠 1 秒便于观察动画，再弹出「表单字段值」通知；
 * complete 回调里隐藏表单遮罩。
 */
@FineUIPage("other/form-ajax-complete")
public class FormAjaxComplete extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 自定义回发（F.doPostBack eventName='Form1_Submit'）统一入口。 */
    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("Form1_Submit".equals(e.getEventName())) {
            // 为了观察前台动画，后台休眠 1 秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            showNotifyRaw("表单字段值：<ul class=\"result\"><li>电子邮箱: " + esc(TextBox2.getValue())
                    + "</li><li>审批人: " + esc(DropDownList3.getText())
                    + "</li><li>申请数量: " + esc(NumberBox1.getValue())
                    + "</li><li>描述: " + esc(TextArea1.getValue()) + "</li></ul>");
        }
    }

    private static String esc(String s) {
        return s == null ? "" : s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    // 表单字段（按 id 反射注入；仅回发读取用）
    com.fineui.java.core.controls.TextBox TextBox2;
    com.fineui.java.core.controls.DropDownList DropDownList3;
    com.fineui.java.core.controls.NumberBox NumberBox1;
    com.fineui.java.core.controls.TextArea TextArea1;
}
