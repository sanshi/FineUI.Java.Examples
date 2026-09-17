package com.fineui.java.examples.mobile.datalist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.mobile.MobilePageBase;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 移动端列表外链演示页（路由 {@code mobile/data-list/data-list-link}）：每项带右侧箭头，点击在新窗口打开对应国家的百科页面。
 */
@FineUIPage("mobile/data-list/data-list-link")
public class DataListLink extends MobilePageBase {

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
            String name = String.valueOf(row.get("Name"));
            String navigateUrl = "http://baike.baidu.com/item/" + URLEncoder.encode(name, StandardCharsets.UTF_8);
            DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE, iconUrl, name, row.get("Desc")),
                    null, true, false, null, navigateUrl, "_blank", true);
        }
    }
}
