package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 页面回发提示演示页面模型类（路由 {@code other/ajax-loading}）：各按钮以不同的 Ajax 提示风格回发，
 * 后台统一休眠 1 秒便于观察提示信息。提示风格经按钮标签的 {@code ajax-loading-type}/
 * {@code show-ajax-loading-mask-text}/{@code ajax-loading-mask-text} 声明，由桥接层在回发期间显示遮罩文本。
 */
@FineUIPage("other/ajax-loading")
public class AjaxLoading extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 所有按钮共用的回发处理器：休眠 1 秒，便于观察 Ajax 提示信息。 */
    public void Button1_Click(Object sender, EventArgs e) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
