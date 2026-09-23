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
 * 移动端分组列表跳转详情页（路由 {@code mobile/data-list/data-list-link-to-panel-group}）：列表按「亚洲/欧洲/美洲」
 * 分组显示组头，每项可点（带箭头），点击左滑进入详情。
 */
@FineUIPage("mobile/data-list/data-list-link-to-panel-group")
public class DataListLinkToPanelGroup extends MobilePageBase {

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
            DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE, iconUrl, row.get("Name"), row.get("Desc")),
                    null, true, false, String.valueOf(row.get("Group")), "#", null, true);
        }
    }
}
