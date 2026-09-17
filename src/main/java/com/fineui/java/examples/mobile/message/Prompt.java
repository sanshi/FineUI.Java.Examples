package com.fineui.java.examples.mobile.message;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.mobile.MobilePageBase;

/**
 * 移动端输入对话框演示页（路由 {@code mobile/message/prompt}）：各按钮弹出输入对话框（{@code F.prompt}），
 * 点确定后把用户输入以居中通知回显。点确定的回调是客户端全局函数 {@code notifyit}（按名调用，无 eval）。
 */
@FineUIPage("mobile/message/prompt")
public class Prompt extends MobilePageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        showPrompt("请输入你的姓名？", "请输入", MessageBoxIcon.Question, "notifyit");
    }

    public void Button2_Click(Object sender, EventArgs e) {
        // 按钮填满 + 标题居中 + 无关闭按钮
        showPrompt("请输入你的姓名？", "请输入", MessageBoxIcon.Question, "notifyit",
                true, false, false, "center", false, null);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        // 简洁按钮 + 取消按钮在前 + 标题居中 + 无关闭按钮
        showPrompt("请输入你的姓名？", "请输入", MessageBoxIcon.Question, "notifyit",
                false, true, true, "center", false, null);
    }

    public void Button4_Click(Object sender, EventArgs e) {
        // 简洁按钮 + 密码输入框 + 标题居中 + 无关闭按钮
        showPrompt("请输入你的密码？", "请输入", MessageBoxIcon.Question, "notifyit",
                false, true, false, "center", false, "password");
    }

    public void Button5_Click(Object sender, EventArgs e) {
        // 简洁按钮 + 标题居中 + 无关闭按钮
        showPrompt("请输入你的姓名？", "请输入", MessageBoxIcon.Question, "notifyit",
                false, true, false, "center", false, null);
    }
}
