package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 单元格样式示例（路由 {@code grid-data-url/cell-style}）：列的 {@code rendererFunction}
 * 客户端函数在返回内容的同时按单元格数据设置样式。数据由 {@code data-url} 按需拉取，服务端无需绑定数据。
 */
@FineUIPage("grid-data-url/cell-style")
public class CellStyle extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取，服务端不绑定。
    }
}
