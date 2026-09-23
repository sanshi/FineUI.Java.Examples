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
 * 移动端分组列表 + 条件箭头页（路由 {@code mobile/data-list/data-list-link-to-panel-group-arrow}）：分组显示，
 * 「中国」项用特殊模板且不可点（无链接、无箭头）；其余项可点，仅「欧洲」组的项显示右侧箭头。
 */
@FineUIPage("mobile/data-list/data-list-link-to-panel-group-arrow")
public class DataListLinkToPanelGroupArrow extends MobilePageBase {

    /** 页面私有模板：描述行多一个 {@code china} 类（长文本换行显示），供「中国」项使用。 */
    private static final String DATALIST_ITEM_TEMPLATE_CHINA =
            "<table class=\"item-table\"><tr><td><img class=\"item-img\" src=\"%s\"><div class=\"item-text\">%s</div><div class=\"item-desc china\">%s</div></td></tr></table>";

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
            String groupName = String.valueOf(row.get("Group"));

            if ("中国".equals(name)) {
                // 中国项：特殊模板、不设链接与箭头（不可点进详情）
                DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE_CHINA, iconUrl, name, row.get("Desc")),
                        null, true, false, groupName, null, null, false);
            } else {
                // 其余项：用普通占位地址保留链接语义，仅欧洲组显示右侧箭头。
                boolean showArrow = "欧洲".equals(groupName);
                DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE, iconUrl, name, row.get("Desc")),
                        null, true, false, groupName, "#", null, showArrow);
            }
        }
    }
}
