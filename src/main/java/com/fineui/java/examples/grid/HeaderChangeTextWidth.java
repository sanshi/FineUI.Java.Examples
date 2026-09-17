package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 改变标题栏文本和宽度（路由 {@code grid/header-change-text-width}）：服务端运行时改变[入学年份]列的标题文本与宽度。
 */
@FineUIPage("grid/header-change-text-width")
public class HeaderChangeTextWidth extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Grid1.updateColumnHeader("EntranceYear", "入学年份（已修改）", 200);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.updateColumnHeader("EntranceYear", "入学年份", 120);
    }
}
