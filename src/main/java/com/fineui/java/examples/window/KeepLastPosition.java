package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体保持位置与大小示例（路由 {@code window/keep-last-position}）：窗体设置
 * {@code keep-last-position="true"} 与 {@code keep-last-size="true"}，关闭后再打开时保持关闭时的位置和大小。
 */
@FineUIPage("window/keep-last-position")
public class KeepLastPosition extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
