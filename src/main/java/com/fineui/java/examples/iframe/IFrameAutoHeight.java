package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * IFrame 面板高度自适应（路由 {@code iframe/iframe-auto-height}）：面板 {@code Panel1} 监听
 * {@code iframeload} 事件，在 {@code onIFrameLoad} 中读取 IFrame 子页实际高度并 {@code setHeight}，
 * 使面板高度随 IFrame 内容自适应；点击按钮经 {@code setHeight('auto') + setIFrameUrl} 切换子页后
 * 会触发 {@code iframeload} 重新计算高度。全部为客户端脚本行为，页面无服务端逻辑。
 */
@FineUIPage("iframe/iframe-auto-height")
public class IFrameAutoHeight extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }
}
