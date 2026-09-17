package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 表头过滤（POST）（路由 {@code grid-data-url/filter}）：表格通过 {@code data-url} + {@code data-method=POST}
 * 把当前过滤条件回传给数据接口 {@code /grid-data-url/filter-data}，由该接口就地过滤后返回数据。页面本身无需服务端过滤逻辑。
 */
@FineUIPage("grid-data-url/filter")
public class Filter extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 过滤在 data-url 指向的 POST 接口内完成，页面不做服务端过滤处理。
    }
}
