package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.enums.TextAlign;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.GroupField;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.core.enums.FieldType;
import java.util.List;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 增删列与多表头：用列集合直接替换为多表头，也可清空后恢复。这里使用已有字段，不重新查询数据；增加新的业务字段时应重新绑定。 */
@FineUIPage("grid-dynamic/columns-collection")
public class ColumnsCollection extends PageBase {
    Grid Grid1;
    Label LabelResult;

    public void Button5_Click(Object sender, EventArgs e) {
        GridColumn column = Grid1.findColumn("Name");
        if (column == null) {
            column = Grid1.findColumn("NewName");
        }
        if (column != null) {
            column.setWidth(180);
            column.setTextAlign(TextAlign.Center);
            column.setHeaderTextAlign(TextAlign.Right);
        }
    }

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    private static RenderField createColumn(String id, String field, String title, FieldType type) {
        RenderField column = new RenderField();
        column.setColumnId(id);
        column.setDataField(field);
        column.setHeaderText(title);
        if (type != null) {
            column.setFieldType(type);
        }
        return column;
    }
    public void Button1_Click(Object sender, EventArgs e) {
        GroupField group = new GroupField();
        group.setHeaderText("学生信息");
        group.addColumn(createColumn("NewName", "Name", "姓名", null));
        group.addColumn(createColumn("NewYear", "EntranceYear", "入学年份", FieldType.Int));
        Grid1.setColumns(List.of(group));
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setColumns(List.of(
                createColumn("Name", "Name", "姓名", null),
                createColumn("EntranceYear", "EntranceYear", "入学年份", FieldType.Int)));
    }

    public void Button3_Click(Object sender, EventArgs e) {
        LabelResult.setText("当前列数：" + Grid1.getColumns().size() + "；分页：" + Grid1.isAllowPaging()
                + "；页大小：" + Grid1.getPageSize() + "；排序：" + Grid1.isAllowSorting()
                + "；多选：" + Grid1.isEnableMultiSelect());
    }

    public void BusinessButton_Click(Object sender, EventArgs e) {
        LabelResult.setText("业务按钮仍可回发。");
    }

    public void Button4_Click(Object sender, EventArgs e) {
        Grid1.getColumns().clear();
    }
}
