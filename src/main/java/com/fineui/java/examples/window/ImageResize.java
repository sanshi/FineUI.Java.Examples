package com.fineui.java.examples.window;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 窗体内图片随尺寸自适应演示页（路由 {@code window/image-resize}）：窗体内容为一张图片，
 * 监听窗体的 render 与 resize 事件，用客户端脚本在保持长宽比的前提下让图片随窗体大小居中缩放。
 */
@FineUIPage("window/image-resize")
public class ImageResize extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
