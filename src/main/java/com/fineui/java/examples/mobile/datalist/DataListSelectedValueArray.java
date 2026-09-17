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
 * 移动端列表多选演示页（路由 {@code mobile/data-list/data-list-selected-value-array}）：初始选中「中国、美国」，
 * 点新项会替换当前选择（不累积）。点「获取选中项」回发读取当前多选值并弹出。
 */
@FineUIPage("mobile/data-list/data-list-selected-value-array")
public class DataListSelectedValueArray extends MobilePageBase {

    DataList DataList1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        DataList1.clearData();
        for (Map<String, Object> row : DataSourceUtil.getCountryTable()) {
            String iconUrl = IconHelper.resolveUrl("/res/icon/flag_" + row.get("Image") + ".png");
            DataList1.addItem(new RawHtml(DATALIST_SIMPLE_ITEM_TEMPLATE, iconUrl, row.get("Name")),
                    String.valueOf(row.get("Id")), true, false, null, null, null, false);
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        showAlert("选中项：" + String.join(", ", DataList1.getSelectedValueArray()));
    }
}
