package com.fineui.java.examples.form;

import com.fineui.java.core.RawHtml;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.PageBase;

import java.util.List;

/**
 * 复选框列表 - 自适应列宽演示页（路由 {@code form/check-box-list-auto-column-width}）：
 * {@code AutoColumnWidth=true} 时各项按内容自适应宽度（每项宽度不同），而非等宽平分。
 */
@FineUIPage("form/check-box-list-auto-column-width")
public class CheckBoxListAutoColumnWidth extends PageBase {

    com.fineui.java.core.controls.CheckBoxList CheckBoxList1;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList2;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList3;
    com.fineui.java.core.controls.CheckBoxList CheckBoxList4;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        List<TestClass> dataSource = List.of(
                new TestClass("item1", "数据绑定值 1"),
                new TestClass("item2", "数据绑定值 2"),
                new TestClass("item3", "数据绑定值 3"),
                new TestClass("item4", "数据绑定值 4")
        );

        CheckBoxList2.setDataTextField("name");
        CheckBoxList2.setDataValueField("id");
        CheckBoxList2.setDataSource(dataSource);
        CheckBoxList2.dataBind();
        CheckBoxList2.setSelectedValueArray(new String[] {"item1", "item3"});
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotify(new RawHtml(FormSummary.of(CheckBoxList1, CheckBoxList2, CheckBoxList3, CheckBoxList4)));
    }

    public void CheckBoxList3_SelectedIndexChanged(Object sender, EventArgs e) {
        showNotify("列表三的选中项：" + String.join(", ", CheckBoxList3.getSelectedValueArray()));
    }

    public void btnServerSetSelectedValue_Click(Object sender, EventArgs e) {
        CheckBoxList1.setSelectedValueArray(new String[] {"value1", "value3"});
    }

    public void btnServerGetSelectedValue_Click(Object sender, EventArgs e) {
        if (CheckBoxList1.getSelectedValueArray().length > 0) {
            showNotify("列表一的选中项：" + String.join(", ", CheckBoxList1.getSelectedValueArray()));
        } else {
            showNotify("列表一没有选中项！");
        }
    }

    /** 示例数据中的 id 作为选中值，name 作为显示文本。 */
    public record TestClass(String id, String name) {
    }
}
