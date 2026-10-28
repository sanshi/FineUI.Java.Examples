package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 后台直接修改列的标题和宽度，并在下一次回发中读取当前属性。 */
@FineUIPage("grid/header-change-properties")
public class HeaderChangeProperties extends PageBase {
    Grid Grid1;
    Label LabelResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    public void Button1_Click(Object sender, EventArgs e) {
        // 按声明中的 column-id 找到目标列，只改显示属性，无需重新查询或绑定表格数据。
        GridColumn column = Grid1.findColumn("EntranceYear");
        column.setHeaderText("入学年份（已修改）");
        column.setWidth(200);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        GridColumn column = Grid1.findColumn("EntranceYear");
        column.setHeaderText("入学年份");
        column.setWidth(120);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        GridColumn column = Grid1.findColumn("EntranceYear");
        LabelResult.setText("当前标题：" + column.getHeaderText() + "；当前宽度：" + column.getWidth() + " 像素");
    }
}
