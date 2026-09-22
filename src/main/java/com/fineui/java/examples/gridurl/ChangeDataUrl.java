package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 切换数据源（路由 {@code grid-data-url/change-data-url}）：两个按钮在两个 {@code data-url}
 * 间来回切换——一个纯客户端（{@code loadDataUrl}），一个回发服务端（改 {@code dataUrl}）。用 {@code data-source-key}
 * 自定义属性记住当前处于哪个数据源，回发时随控件状态带回。
 */
@FineUIPage("grid-data-url/change-data-url")
public class ChangeDataUrl extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        String sourceKey = currentSourceKey();
        if (sourceKey == null || sourceKey.isEmpty() || "source2".equals(sourceKey)) {
            Grid1.setAttribute("data-source-key", "source1");
            Grid1.setDataUrl("/grid-data-url/grid-data-url-data");
        } else {
            Grid1.setAttribute("data-source-key", "source2");
            Grid1.setDataUrl("/grid-data-url/grid-data-url-data?data2=true");
        }
    }

    public void Button2_Click(Object sender, EventArgs e) {
        loadData();
        // 属性保存地址，命令负责真实取数；仅修改 dataUrl 不会重新加载表格。
        Grid1.loadDataUrl(Grid1.getDataUrl());
    }

    public void btnReload_Click(Object sender, EventArgs e) {
        Grid1.loadDataUrl();
    }

    /** 读回带的自定义属性 data-source-key（记住当前处于哪个数据源）。 */
    private String currentSourceKey() {
        return Grid1.getAttribute("data-source-key");
    }
}
