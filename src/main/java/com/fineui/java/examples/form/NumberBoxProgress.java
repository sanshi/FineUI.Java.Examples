package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 数字输入框·进度条演示页（路由 {@code form/number-box-progress}）：DisplayType=Progress 把数字渲染为进度条，
 * 进度值宽度随数值变化，ProgressTextVisible / ProgressTextInside / ProgressHeight 控制文本与高度；
 * 底部按钮用客户端脚本批量改变所有进度条的值。全部为纯客户端交互，无服务端事件。
 */
@FineUIPage("form/number-box-progress")
public class NumberBoxProgress extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
