package com.fineui.java.examples.mobile.datalist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.mobile.MobilePageBase;

import java.util.Map;

/**
 * 移动端列表单选演示页（路由 {@code mobile/data-list/data-list-selected-value}）：列表初始选中「法国」，
 * 点「获取选中项」回发读取当前单选选中值并弹出。
 */
@FineUIPage("mobile/data-list/data-list-selected-value")
public class DataListSelectedValue extends MobilePageBase {

    DataList DataList1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        loadData(false);
    }

    private void loadData(boolean reverse) {
        DataList1.clearData();
        java.util.List<Map<String, Object>> source = new java.util.ArrayList<>(DataSourceUtil.getCountryTable());
        if (reverse) {
            java.util.Collections.reverse(source);
        }
        for (Map<String, Object> row : source) {
            String iconUrl = IconHelper.resolveUrl("/res/icon/flag_" + row.get("Image") + ".png");
            DataList1.addItem(new RawHtml(DATALIST_SIMPLE_ITEM_TEMPLATE, iconUrl, row.get("Name")),
                    String.valueOf(row.get("Id")), true, false, null, null, null, false);
        }
    }

    /** 服务端设置选择，替换当前可取消的选中项。 */
    public void btnSetSelection_Click(Object sender, EventArgs e) {
        DataList1.setSelectedValue("cn");
    }

    /** 服务端清空选择。 */
    public void btnClearSelection_Click(Object sender, EventArgs e) {
        DataList1.setSelectedValue(null);
    }

    /** 倒序重绑同一批列表项，并选中美国。 */
    public void btnRebindSelection_Click(Object sender, EventArgs e) {
        loadData(true);
        DataList1.setSelectedValue("us");
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        String selectedValue = DataList1.getSelectedValue();
        showAlert("选中项：" + (selectedValue == null ? "" : selectedValue));
    }
}
