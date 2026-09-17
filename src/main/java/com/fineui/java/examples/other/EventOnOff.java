package com.fineui.java.examples.other;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 客户端事件处理（新增与删除）演示页面模型类（路由 {@code other/event-on-off}）：全部为纯客户端交互——
 * 用 {@code F.ui.btnTest.on('click', fn)} 动态注册 / {@code off('click', fn)} 移除 / {@code off('click')}
 * 清空点击事件监听器，并在一个 {@code <ul id="result">} 中累计展示触发记录。无服务端事件。
 */
@FineUIPage("other/event-on-off")
public class EventOnOff extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
