package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.GridPageEventArgs;
import com.fineui.java.core.GridSortEventArgs;
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
import com.fineui.java.examples.code.StudentGridData2;

import java.util.ArrayList;
import java.util.List;

/** 动态创建列 + 排序（路由 {@code grid-dynamic/dynamic-columns-sorting}）：切换「班级 / 学生」表格并演示排序局限。 */
@FineUIPage("grid-dynamic/dynamic-columns-sorting")
public class DynamicColumnsSorting extends PageBase {

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
            Grid1.setTitle("表格一（班级）");
            Grid1.configColumns(createClassColumns(), new GridConfigOptions()
                    .setDataIdField("Id").setDataTextField("Name").setEnableCheckBoxSelect(false),
                    ClassGridData.rows());
        } else {
            Grid1.setTitle("表格二（学生，排序）");
            Grid1.configColumns(createStudentColumns(), new GridConfigOptions()
                    .setDataIdField("Id").setDataTextField("Name").setEnableCheckBoxSelect(true),
                    StudentGridData2.pagedSorted(0, 5, "Name", "ASC"));
        }
    }

    public void Grid1_Sort(Object sender, GridSortEventArgs e) {
        loadStudentData();
    }

    public void Grid1_PageIndexChanged(Object sender, GridPageEventArgs e) {
        loadStudentData();
    }

    private void loadStudentData() {
        Grid1.setRecordCount(StudentGridData2.count());
        Grid1.setDataSource(StudentGridData2.pagedSorted(Grid1.getPageIndex(), 5,
                Grid1.getSortField(), Grid1.getSortDirection()));
        Grid1.dataBind();
    }

    private List<GridColumn> createClassColumns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());
        columns.add(field("班级名", "Name", null, null, 0, "Name"));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100, "EntranceYear"));
        RenderField logTime = field("注册日期", "LogTime", FieldType.Date, null, 100, null);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        columns.add(logTime);
        RenderField desc = field("描述", "Desc", null, null, 0, null);
        desc.setExpandUnusedSpace(true);
        columns.add(desc);
        return columns;
    }

    private List<GridColumn> createStudentColumns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());
        columns.add(field("姓名", "Name", null, null, 0, "Name"));
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80, "Gender"));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100, "EntranceYear"));
        RenderCheckField atSchool = new RenderCheckField();
        atSchool.setHeaderText("是否在校");
        atSchool.setDataField("AtSchool");
        atSchool.setRenderAsStaticField(true);
        atSchool.setWidth(100);
        columns.add(atSchool);
        // RF 此列还设 Enabled=false（禁用交互）；Java RenderCheckField/GridColumn 目前没有 Enabled 概念，框架侧缺口
        RenderCheckField atSchoolInteractive = new RenderCheckField();
        atSchoolInteractive.setHeaderText("是否在校");
        atSchoolInteractive.setDataField("AtSchool");
        atSchoolInteractive.setRenderAsStaticField(false);
        columns.add(atSchoolInteractive);
        RenderField major = field("所学专业", "Major", null, "renderMajor", 0, null);
        major.setExpandUnusedSpace(true);
        columns.add(major);
        columns.add(field("分组", "Group", null, "renderGroup", 80, null));
        RenderField logTime = field("注册日期", "LogTime", FieldType.Date, null, 100, null);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        columns.add(logTime);
        return columns;
    }

    private static RenderField field(String header, String dataField, FieldType type, String rendererFn, int width, String sortField) {
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
        if (sortField != null) {
            f.setSortField(sortField);
        }
        return f;
    }
}
