package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBoxList;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.form.FormSummary;

import java.util.List;
import java.util.Map;

/** 表格式复选框列表示例（路由 {@code form-table/table-style-check-box-list}）：多列/竖排复选列表、数据绑定、服务端设/取选中项、选中改变事件。 */
@FineUIPage("form-table/table-style-check-box-list")
public class TableStyleCheckBoxList extends PageBase {

    protected SimpleForm SimpleForm1;
    protected CheckBoxList CheckBoxList1;
    protected CheckBoxList CheckBoxList2;
    protected CheckBoxList CheckBoxList3;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        List<Map<String, String>> myList = List.of(
                Map.of("Id", "item1", "Name", "数据绑定值 1"),
                Map.of("Id", "item2", "Name", "数据绑定值 2"),
                Map.of("Id", "item3", "Name", "数据绑定值 3"),
                Map.of("Id", "item4", "Name", "数据绑定值 4"));
        CheckBoxList2.setDataTextField("Name");
        CheckBoxList2.setDataValueField("Id");
        CheckBoxList2.setDataSource(myList);
        CheckBoxList2.dataBind();
        CheckBoxList2.setSelectedValueArray(new String[] {"item1", "item3"});
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showNotifyRaw(FormSummary.ofForm(SimpleForm1));
    }

    public void CheckBoxList3_SelectedIndexChanged(Object sender, EventArgs e) {
        showNotify(String.format("列表三的选中项：%s", String.join(", ", CheckBoxList3.getSelectedValueArray())));
    }

    public void btnServerSetSelectedValue_Click(Object sender, EventArgs e) {
        CheckBoxList1.setSelectedValueArray(new String[] {"value1", "value3"});
    }

    public void btnServerGetSelectedValue_Click(Object sender, EventArgs e) {
        String[] selected = CheckBoxList1.getSelectedValueArray();
        if (selected.length > 0) {
            showNotify(String.format("列表一的选中项：%s", String.join(", ", selected)));
        } else {
            showNotify("列表一没有选中项！");
        }
    }
}
