package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumn;
import com.fineui.java.core.controls.RenderCheckField;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.core.controls.RowNumberField;
import com.fineui.java.core.enums.FieldType;
import com.fineui.java.core.enums.Renderer;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

import java.util.ArrayList;
import java.util.List;

/** 动态创建列（路由 {@code grid-dynamic/dynamic-columns}）：列在服务端首屏用代码构建，而非模板声明。 */
@FineUIPage("grid-dynamic/dynamic-columns")
public class DynamicColumns extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.getColumns().clear();
            for (GridColumn column : createColumns()) {
                Grid1.addColumn(column);
            }
            Grid1.setDataSource(StudentGridData.rows());
            Grid1.dataBind();
        }
    }

    private List<GridColumn> createColumns() {
        List<GridColumn> columns = new ArrayList<>();
        columns.add(new RowNumberField());

        columns.add(field("姓名", "Name", null, null, 0, null));
        columns.add(field("性别", "Gender", FieldType.Int, "renderGender", 80, null));
        columns.add(field("入学年份", "EntranceYear", FieldType.Int, null, 100, null));

        RenderCheckField staticCheck = new RenderCheckField();
        staticCheck.setHeaderText("是否在校");
        staticCheck.setDataField("AtSchool");
        staticCheck.setRenderAsStaticField(true);
        staticCheck.setWidth(100);
        columns.add(staticCheck);

        RenderCheckField interactiveCheck = new RenderCheckField();
        interactiveCheck.setHeaderText("是否在校");
        interactiveCheck.setDataField("AtSchool");
        interactiveCheck.setRenderAsStaticField(false);
        columns.add(interactiveCheck);

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

    private static RenderField field(String header, String dataField, FieldType type, String rendererFn, int width, String unused) {
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
