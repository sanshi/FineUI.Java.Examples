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

/**
 * 动态创建列 + 表头过滤（路由 {@code grid-dynamic/dynamic-columns-filters}）：下拉框在两套表格间切换，
 * 用代码在服务端重配表格列并绑定数据。表头过滤能力暂不支持，本页仅演示动态换列。
 */
@FineUIPage("grid-dynamic/dynamic-columns-filters")
public class DynamicColumnsFilters extends PageBase {

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
            Grid1.setTitle("表格一");
            // 重新配置表头并绑定数据
            Grid1.configColumns(createGrid1Columns(), new GridConfigOptions()
                            .setDataIdField("Id").setDataTextField("Name")
                            .setEnableCheckBoxSelect(false).setEnableMultiSelect(false),
                    StudentGridData.rows());
        } else {
            Grid1.setTitle("表格二（表头过滤）");
            // 重新配置表头并绑定数据
            Grid1.configColumns(createGrid2Columns(), new GridConfigOptions()
                            .setDataIdField("Id").setDataTextField("Name")
                            .setEnableCheckBoxSelect(true).setEnableMultiSelect(true),
                    StudentGridData2.rows());
        }
    }

    private List<GridColumn> createGrid1Columns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());
        columns.add(field("姓名", "Name", null, null, 0));
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100));

        RenderCheckField atSchool = new RenderCheckField();
        atSchool.setHeaderText("是否在校");
        atSchool.setDataField("AtSchool");
        atSchool.setRenderAsStaticField(true);
        columns.add(atSchool);

        RenderField major = field("所学专业", "Major", null, "renderMajor", 0);
        major.setExpandUnusedSpace(true);
        columns.add(major);

        columns.add(field("分组", "Group", null, "renderGroup", 80));

        RenderField logTime = field("注册日期", "LogTime", FieldType.Date, null, 100);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        columns.add(logTime);

        return columns;
    }

    private List<GridColumn> createGrid2Columns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(field("姓名", "Name", null, null, 200));
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100));

        RenderCheckField atSchool = new RenderCheckField();
        atSchool.setHeaderText("是否在校");
        atSchool.setDataField("AtSchool");
        atSchool.setRenderAsStaticField(true);
        columns.add(atSchool);

        columns.add(field("语文成绩", "ChineseScore", null, null, 0));
        columns.add(field("数学成绩", "MathScore", null, null, 0));

        RenderField total = field("总成绩", "TotalScore", null, null, 0);
        total.setLocked(true);
        columns.add(total);

        return columns;
    }

    private static RenderField field(String header, String dataField, FieldType type, String rendererFn, int width) {
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
        return f;
    }
}
