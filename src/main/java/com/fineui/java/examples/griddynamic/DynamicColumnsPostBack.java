package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.GridConfigOptions;
import com.fineui.java.core.controls.GroupField;
import com.fineui.java.core.controls.RenderCheckField;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.core.controls.RowNumberField;
import com.fineui.java.core.enums.FieldType;
import com.fineui.java.core.enums.Renderer;
import com.fineui.java.core.enums.TextAlign;
import com.fineui.java.examples.code.GroupFieldGridData;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.List;

/** 动态创建列 + 回发切换（路由 {@code grid-dynamic/dynamic-columns-post-back}）：下拉切换时运行时整体重配列。 */
@FineUIPage("grid-dynamic/dynamic-columns-post-back")
public class DynamicColumnsPostBack extends PageBase {

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
            Grid1.setTitle("表格一（单选，行扩展列）");
            Grid1.configColumns(createTable1Columns(), new GridConfigOptions()
                    .setDataIdField("Id").setDataTextField("Name")
                    .setEnableCheckBoxSelect(false).setEnableMultiSelect(false), StudentGridData.rows());
        } else {
            Grid1.setTitle("表格二（多选，多表头，全选列）");
            Grid1.configColumns(createTable2Columns(), new GridConfigOptions()
                    .setDataIdField("Id").setDataTextField("Year")
                    .setEnableCheckBoxSelect(true).setEnableMultiSelect(true), GroupFieldGridData.rows());
        }
    }

    private List<GridColumn> createTable1Columns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(field("姓名", "Name", null, null, 0));
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100));

        RenderCheckField staticCheck = new RenderCheckField();
        staticCheck.setHeaderText("是否在校");
        staticCheck.setDataField("AtSchool");
        staticCheck.setRenderAsStaticField(true);
        columns.add(staticCheck);

        RenderCheckField interactiveCheck = new RenderCheckField();
        interactiveCheck.setHeaderText("是否在校");
        interactiveCheck.setDataField("AtSchool");
        interactiveCheck.setRenderAsStaticField(false);
        interactiveCheck.setEnableColumnEdit(false);
        columns.add(interactiveCheck);

        RenderField major = field("所学专业", "Major", null, "renderMajor", 0);
        major.setExpandUnusedSpace(true);
        columns.add(major);

        columns.add(field("分组", "Group", null, "renderGroup", 80));

        RenderField logTime = field("注册日期", "LogTime", FieldType.Date, null, 100);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        columns.add(logTime);

        RenderField expander = new RenderField();
        expander.setHeaderText("个人简介");
        expander.setDataField("Desc");
        expander.setRenderAsRowExpander(true);
        columns.add(expander);

        return columns;
    }

    private List<GridColumn> createTable2Columns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());
        columns.add(field("统计年份", "Year", null, null, 80));

        GroupField anhui = group("安徽省");
        GroupField hefei = group("合肥市");
        hefei.addColumn(field("数据一", "AHData1", null, null, 100));
        hefei.addColumn(field("数据二", "AHData2", null, null, 100));
        anhui.addColumn(hefei);
        columns.add(anhui);

        GroupField henan = group("河南省");
        GroupField zhumadian = group("驻马店市");
        zhumadian.addColumn(field("数据一", "HZData1", null, null, 100));
        zhumadian.addColumn(field("数据二", "HZData2", null, null, 100));
        henan.addColumn(zhumadian);
        GroupField luohe = group("漯河市");
        luohe.addColumn(field("数据一", "HLData1", null, null, 100));
        luohe.addColumn(field("数据二", "HLData2", null, null, 100));
        henan.addColumn(luohe);
        columns.add(henan);

        RenderField logTime = new RenderField();
        logTime.setHeaderText("记录时间");
        logTime.setDataField("LogTime");
        logTime.setTextAlign(TextAlign.Center);
        logTime.setFieldType(FieldType.Date);
        logTime.setRenderer(Renderer.Date);
        logTime.setRendererArgument("yyyy/MM/dd");
        logTime.setHidden(true);   // 回发动态创建的列默认隐藏「记录时间」
        columns.add(logTime);

        return columns;
    }

    private static GroupField group(String header) {
        GroupField g = new GroupField();
        g.setHeaderText(header);
        g.setTextAlign(TextAlign.Center);
        return g;
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
