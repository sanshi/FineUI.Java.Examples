package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 客户端自定义排序（路由 {@code grid/sorting-client-sorter}）：不定义服务端排序事件即为客户端排序；「所学专业」列用
 * 自定义比较函数 {@code majorSorter}，可在下拉列表间切换「按字母顺序 / 按字符个数」。
 */
@FineUIPage("grid/sorting-client-sorter")
public class SortingClientSorter extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }
}
