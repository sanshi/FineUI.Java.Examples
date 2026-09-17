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
 * 移动端图文列表演示页（路由 {@code mobile/data-list/data-list-image-title}）：每项图标 + 标题 + 描述，
 * 首屏从国家数据源绑定。无交互回发。
 */
@FineUIPage("mobile/data-list/data-list-image-title")
public class DataListImageTitle extends MobilePageBase {

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
                    null, true, false, null, null, null, false);
        }
    }
}
