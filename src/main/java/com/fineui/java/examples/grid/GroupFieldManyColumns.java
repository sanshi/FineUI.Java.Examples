package com.fineui.java.examples.grid;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.GroupField;
import com.fineui.java.core.controls.RenderField;
import com.fineui.java.core.enums.TextAlign;
import com.fineui.java.examples.code.GroupFieldManyColumnsGridData;
import com.fineui.java.examples.code.PageBase;

/**
 * 多表头（大量列，列线对齐）（路由 {@code grid/group-field-many-columns}）：
 * 5 个锁定列 + 12 个月分组（每月「发货」18 列 +「开票」17 列），共 425 个叶子列，
 * 用于验证多级分组表头下表头与表体的竖线对齐。
 * 列是规整的循环结构、数量又大，所以不在模板里逐列声明，全部在这里用代码生成（模板里只留序号列）。
 */
@FineUIPage("grid/group-field-many-columns")
public class GroupFieldManyColumns extends PageBase {

    // 叶子列表头文本：发货用全部 18 个，开票用前 17 个
    private static final String[] SUB_LEAF_NAMES = {
            "金额", "数量", "单价", "去年同期", "去年变化率", "环比", "本年累计", "累计占比", "完成率",
            "目标", "差额", "预测", "实际", "偏差", "均价", "折扣", "税额", "备注"};

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            buildColumns();
            Grid1.setDataSource(GroupFieldManyColumnsGridData.rows());
            Grid1.dataBind();
        }
    }

    // 生成全部列：5 个锁定列 + 12 个月 ×（发货 18 列 + 开票 17 列），共 425 个叶子列
    private void buildColumns() {
        addLockedColumn("客户编号", "khbh", 90);
        addLockedColumn("客户名称", "khmc", 150);
        addLockedColumn("产品", "product", 100);
        addLockedColumn("期初", "qc", 90);
        addLockedColumn("期末", "qm", 90);

        // 12 个月，每月一个分组，月下再分「发货 / 开票」两个子分组
        for (int month = 1; month <= 12; month++) {
            GroupField monthGroup = new GroupField();
            monthGroup.setHeaderText(month + "月");
            monthGroup.setTextAlign(TextAlign.Center);

            GroupField fhGroup = new GroupField();
            fhGroup.setHeaderText("发货");
            fhGroup.setTextAlign(TextAlign.Center);
            for (int i = 0; i < 18; i++) {
                fhGroup.addColumn(leafColumn(SUB_LEAF_NAMES[i], "m" + month + "_fh_" + i));
            }
            monthGroup.addColumn(fhGroup);

            GroupField kpGroup = new GroupField();
            kpGroup.setHeaderText("开票");
            kpGroup.setTextAlign(TextAlign.Center);
            for (int i = 0; i < 17; i++) {
                kpGroup.addColumn(leafColumn(SUB_LEAF_NAMES[i], "m" + month + "_kp_" + i));
            }
            monthGroup.addColumn(kpGroup);

            Grid1.addColumn(monthGroup);
        }
    }

    // 左侧锁定列
    private void addLockedColumn(String headerText, String field, int width) {
        RenderField column = new RenderField();
        column.setHeaderText(headerText);
        column.setDataField(field);
        column.setWidth(width);
        column.setLocked(true);
        Grid1.addColumn(column);
    }

    // 月分组下的叶子列：宽 90、右对齐
    private static RenderField leafColumn(String headerText, String field) {
        RenderField column = new RenderField();
        column.setHeaderText(headerText);
        column.setDataField(field);
        column.setWidth(90);
        column.setTextAlign(TextAlign.Right);
        return column;
    }
}
