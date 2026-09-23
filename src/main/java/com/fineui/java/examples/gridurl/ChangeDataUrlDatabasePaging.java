package com.fineui.java.examples.gridurl;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;

/**
 * 网址数据源 · 切换数据源（数据库分页）（路由 {@code grid-data-url/change-data-url-database-paging}）：
 * 与切换数据源示例相同，但开启数据库分页（每页 5 行）；切换数据源时同时重置为第一页。
 */
@FineUIPage("grid-data-url/change-data-url-database-paging")
public class ChangeDataUrlDatabasePaging extends PageBase {

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
            Grid1.setDataUrl("/grid-data-url/paging-database-data");
        } else {
            Grid1.setAttribute("data-source-key", "source2");
            Grid1.setDataUrl("/grid-data-url/paging-database-data?data2=true");
        }

        // 重置为第一页
        Grid1.setPageIndex(0);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        // 地址变化会自动取数；增量属性先于命令生效，因此页码也会先重置。
        loadData();
    }

    /** 读回带的自定义属性 data-source-key（记住当前处于哪个数据源）。 */
    private String currentSourceKey() {
        return Grid1.getAttribute("data-source-key");
    }
}
