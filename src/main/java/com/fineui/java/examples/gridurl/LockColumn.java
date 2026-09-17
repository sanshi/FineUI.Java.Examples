package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 列锁定（路由 {@code grid-data-url/lock-column}）：开启复选列与列锁定，
 * 姓名列初始锁定在左侧、随横向滚动固定，其余列均可由列头菜单锁定/解锁。数据由 {@code data-url} 拉取。
 */
@FineUIPage("grid-data-url/lock-column")
public class LockColumn extends PageBase {

    public void Page_Load(Object sender, EventArgs e) {
        // 数据由客户端向 data-url 拉取，服务端不绑定。
    }
}
