package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 工具栏图片演示页（路由 {@code toolbar/toolbar-images}）：在面板顶部工具栏中混排图片与链接按钮，
 * 并用脚本在所有图片加载完毕后重新布局，避免图片未加载导致的布局错乱。
 */
@FineUIPage("toolbar/toolbar-images")
public class ToolbarImages extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
