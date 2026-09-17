package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 客户端多列排序（路由 {@code grid/sorting-client-multi}）：不定义 {@code onSort} 服务端事件，
 * 按住 Shift 点击多个列头即在浏览器内存中对当前数据叠加多列排序。初始按性别升序。
 */
@FineUIPage("grid/sorting-client-multi")
public class SortingClientMulti extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.sortedRowsMulti(Grid1.getSortFieldArray()));
        Grid1.dataBind();
    }
}
