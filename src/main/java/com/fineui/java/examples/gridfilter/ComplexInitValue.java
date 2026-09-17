package com.fineui.java.examples.gridfilter;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GridColumnFilteredData;
import com.fineui.java.core.controls.GridColumnFilteredItem;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/**
 * 表头过滤 · 综合 + 服务端初始化过滤值（路由 {@code grid-filter/complex-init-value}）：在「综合」基础上，
 * 首屏由服务端为入学年份（2008&lt;年份&lt;2025）、所学专业（化学系/物理系/数学系）、分组（分组2/分组3）设初始过滤值；
 * 「后台更新过滤数据」按钮清空后改设性别（男）+ 入学年份（2008&lt;年份&lt;2025）。
 */
@FineUIPage("grid-filter/complex-init-value")
public class ComplexInitValue extends PageBase {

    Grid Grid1;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            // 初始化列的过滤数据
            GridColumnFilteredData entranceYear = new GridColumnFilteredData();
            entranceYear.setMulti(true);
            entranceYear.setMatcher("all");
            entranceYear.addItem(new GridColumnFilteredItem("greater", 2008));
            entranceYear.addItem(new GridColumnFilteredItem("less", 2025));
            Grid1.findColumn("EntranceYear").setColumnFilteredData(entranceYear);

            GridColumnFilteredData major = new GridColumnFilteredData();
            major.setMulti(false);
            major.addItem(new GridColumnFilteredItem(null, new String[]{"化学系", "物理系", "数学系"}));
            Grid1.findColumn("Major").setColumnFilteredData(major);

            GridColumnFilteredData group = new GridColumnFilteredData();
            group.setMulti(false);
            group.addItem(new GridColumnFilteredItem(null, new String[]{"2", "3"}));   // 分组2, 分组3
            Grid1.findColumn("Group").setColumnFilteredData(group);

            bindGrid();

            labResult.setText("初始过滤数据：" + Grid1.getFilteredData());
        }
    }

    public void Grid1_FilterChanged(Object sender, EventArgs e) {
        bindGrid();
        labResult.setText("过滤数据：" + Grid1.getFilteredData());
    }

    public void btnUpdateFilteredData_Click(Object sender, EventArgs e) {
        // 先清空过滤数据
        Grid1.setFilteredData(null);

        // 初始化列的过滤数据
        GridColumnFilteredData gender = new GridColumnFilteredData();
        gender.setMulti(false);
        gender.addItem(new GridColumnFilteredItem(null, 1));
        Grid1.findColumn("Gender").setColumnFilteredData(gender);

        GridColumnFilteredData entranceYear = new GridColumnFilteredData();
        entranceYear.setMulti(true);
        entranceYear.setMatcher("all");
        entranceYear.addItem(new GridColumnFilteredItem("greater", 2008));
        entranceYear.addItem(new GridColumnFilteredItem("less", 2025));
        Grid1.findColumn("EntranceYear").setColumnFilteredData(entranceYear);

        bindGrid();

        labResult.setText("后台更新过滤数据：" + Grid1.getFilteredData());
    }

    private void bindGrid() {
        Grid1.setDataSource(FilteredTable.getFilteredRows(StudentGridData.rows(), Grid1, this::filterDataRowItem));
        Grid1.dataBind();
    }

    private boolean filterDataRowItem(Object sourceValue, GridColumnFilteredItem item, String columnId) {
        switch (columnId) {
            case "Name":
                return FilterMatchers.text(item.getOperator(), sourceValue, item.getValue());
            case "EntranceYear":
                return FilterMatchers.number(item.getOperator(), sourceValue, item.getValue());
            case "LogTime":
                return FilterMatchers.date(item.getOperator(), sourceValue, item.getValue());
            case "Major":
            case "Group":
                return FilterMatchers.tags(sourceValue, item.getValue());
            case "AtSchool":
            case "Gender":
                // 单选（RadioButtonList）：源值与选中值按相等匹配
                return FilterMatchers.text("equal", sourceValue, item.getValue());
            default:
                return false;
        }
    }
}
