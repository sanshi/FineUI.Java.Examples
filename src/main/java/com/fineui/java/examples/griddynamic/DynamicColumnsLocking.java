package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.GridConfigOptions;
import com.fineui.java.core.controls.RenderCheckField;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.core.controls.RowNumberField;
import com.fineui.java.core.enums.FieldType;
import com.fineui.java.core.enums.Renderer;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

import java.util.ArrayList;
import java.util.List;

/** 动态创建列 + 列锁定（路由 {@code grid-dynamic/dynamic-columns-locking}）：切换单选表格与多选列锁定表格。 */
@FineUIPage("grid-dynamic/dynamic-columns-locking")
public class DynamicColumnsLocking extends PageBase {

    DropDownList DropDownList1;
    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    public void DropDownList1_SelectedIndexChanged(Object sender, EventArgs e) {
        loadData();
    }

    private void loadData() {
        if ("table1".equals(DropDownList1.getSelectedValue())) {
            Grid1.setTitle("表格一（单选）");
            Grid1.setAllowColumnLocking(false);
            Grid1.configColumns(createTable1Columns(), new GridConfigOptions()
                    .setDataIdField("Id").setDataTextField("Name").setEnableCheckBoxSelect(false).setEnableMultiSelect(false),
                    StudentGridData.rows());
        } else {
            Grid1.setTitle("表格二（多选，列锁定）");
            Grid1.setAllowColumnLocking(true);
            Grid1.configColumns(createTable2Columns(), new GridConfigOptions()
                    .setDataIdField("Id").setDataTextField("Name").setEnableCheckBoxSelect(true).setEnableMultiSelect(true),
                    StudentGridData2.rows());
        }
    }

    private List<GridColumn> createTable1Columns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());
        columns.add(field("姓名", "Name", null, null, 0, false, false));
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80, false, false));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100, false, false));
        RenderCheckField atSchool = new RenderCheckField();
        atSchool.setHeaderText("是否在校");
        atSchool.setDataField("AtSchool");
        atSchool.setRenderAsStaticField(true);
        columns.add(atSchool);
        RenderField major = field("所学专业", "Major", null, "renderMajor", 0, false, false);
        major.setExpandUnusedSpace(true);
        columns.add(major);
        columns.add(field("分组", "Group", null, "renderGroup", 80, false, false));
        RenderField logTime = field("注册日期", "LogTime", FieldType.Date, null, 100, false, false);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        columns.add(logTime);
        return columns;
    }

    private List<GridColumn> createTable2Columns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());
        columns.add(field("姓名", "Name", null, null, 0, true, true));            // 锁定
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80, true, false));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100, true, false));
        RenderCheckField atSchool = new RenderCheckField();
        atSchool.setHeaderText("是否在校");
        atSchool.setDataField("AtSchool");
        atSchool.setRenderAsStaticField(true);
        atSchool.setEnableLock(true);
        columns.add(atSchool);
        RenderField major = field("所学专业", "Major", null, "renderMajor", 0, true, false);
        major.setExpandUnusedSpace(true);
        columns.add(major);
        columns.add(field("分组", "Group", null, "renderGroup", 80, true, false));
        RenderField logTime = field("注册日期", "LogTime", FieldType.Date, null, 100, true, false);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        columns.add(logTime);
        return columns;
    }

    private static RenderField field(String header, String dataField, FieldType type, String rendererFn,
                                     int width, boolean enableLock, boolean locked) {
        RenderField f = new RenderField();
        f.setHeaderText(header);
        f.setDataField(dataField);
        if (type != null) {
            f.setFieldType(type);
        }
        if (rendererFn != null) {
            f.setRendererFunction(rendererFn);
        }
        if (width > 0) {
            f.setWidth(width);
        }
        if (enableLock) {
            f.setEnableLock(true);
        }
        if (locked) {
            f.setLocked(true);
        }
        return f;
    }
}
