package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 图片演示页（路由 {@code form/image}）：具名图标、图片 URL、带尺寸与边框样式的图片；
 * 点击按钮在 32/64 两种尺寸间切换第三张图片（服务端改尺寸、增量推回客户端）。
 */
@FineUIPage("form/image")
public class Image extends PageBase {

    com.fineui.java.core.controls.Image Image3;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void Button1_Click(Object sender, EventArgs e) {
        if (Image3.getImageWidth() == 32) {
            Image3.setImageWidth(64);
            Image3.setImageHeight(64);
        } else {
            Image3.setImageWidth(32);
            Image3.setImageHeight(32);
        }
    }
}
