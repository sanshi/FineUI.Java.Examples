package com.fineui.java.examples.griddynamic;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;

/** 修改表格显示与选择方式：直接设置表格属性即可改变选择列和外观，不需要重新赋值 Columns 或绑定数据。可先选中多行，再切回单选。 */
@FineUIPage("grid-dynamic/display-selection")
public class DisplaySelection extends PageBase {
    Grid Grid1;
    Label LabelResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    public void Button1_Click(Object sender, EventArgs e) {
        Grid1.setEnableCheckBoxSelect(true);
        Grid1.setEnableMultiSelect(true);
        Grid1.setEnableRowLines(false);
        Grid1.setEnableAlternateRowColor(true);
    }

    public void Button2_Click(Object sender, EventArgs e) {
        Grid1.setEnableCheckBoxSelect(false);
        Grid1.setEnableMultiSelect(false);
        Grid1.setEnableRowLines(true);
        Grid1.setEnableAlternateRowColor(false);
    }

    public void Button3_Click(Object sender, EventArgs e) {
        LabelResult.setText("复选框选择列：" + Grid1.isEnableCheckBoxSelect()
                + "；多选：" + Grid1.isEnableMultiSelect()
                + "；行分隔线：" + Grid1.isEnableRowLines()
                + "；交替行颜色：" + Grid1.isEnableAlternateRowColor());
    }

    public void BusinessButton_Click(Object sender, EventArgs e) {
        LabelResult.setText("业务按钮仍可回发。");
    }
}
