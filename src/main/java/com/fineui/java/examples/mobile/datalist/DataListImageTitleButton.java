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
 * 移动端图文列表 + 行内按钮演示页（路由 {@code mobile/data-list/data-list-image-title-button}）：每项右侧留一个
 * 操作单元格，客户端 {@code F.ready} 动态建「详情」按钮；点按钮把该项信息填入 Panel2 并左滑进入详情，后退右滑返回。
 */
@FineUIPage("mobile/data-list/data-list-image-title-button")
public class DataListImageTitleButton extends MobilePageBase {

    /** 页面私有列表项模板：在图文模板基础上多一个右侧操作单元格 {@code <td class="actions">}（供动态建按钮）。 */
    private static final String DATALIST_ITEM_TEMPLATE_ACTIONS =
            "<table class=\"item-table\"><tr><td><img class=\"item-img\" src=\"%s\"><div class=\"item-text\">%s</div><div class=\"item-desc\">%s</div></td><td class=\"actions\"></td></tr></table>";

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
            DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE_ACTIONS, iconUrl, row.get("Name"), row.get("Desc")),
                    null, true, false, null, null, null, false);
        }
    }
}
