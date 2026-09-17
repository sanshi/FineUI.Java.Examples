package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 排序示例（路由 {@code grid-data-url/sorting}）：表格开启排序，
 * 数据由 {@code data-url} 指向的接口按需拉取 JSON 渲染。服务端无需绑定数据。
 */
@FineUIPage("grid-data-url/sorting")
public class Sorting extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取，服务端不绑定。
    }
}
