package com.fineui.java.examples.mobile.datalist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.mobile.MobilePageBase;

import java.util.List;
import java.util.Map;

/**
 * 移动端列表「重新绑定」演示页（路由 {@code mobile/data-list/data-list-data-bind}）：点「重新绑定」在两套
 * 数据源之间来回切换。当前数据源标识用一个隐藏字段跨回发保持。
 */
@FineUIPage("mobile/data-list/data-list-data-bind")
public class DataListDataBind extends MobilePageBase {

    DataList DataList1;
    HiddenField hfSourceKey;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        autoBind();
    }

    /** 根据隐藏字段里记录的「当前数据源」标识，切到另一套数据源并回写标识。 */
    private void autoBind() {
        String sourceKey = hfSourceKey.getText();
        if (sourceKey == null || sourceKey.isEmpty() || "source2".equals(sourceKey)) {
            bindList(DataSourceUtil.getCountryTable());
            sourceKey = "source1";
        } else {
            bindList(DataSourceUtil.getCountryTable2());
            sourceKey = "source2";
        }
        hfSourceKey.setText(sourceKey);
    }

    private void bindList(List<Map<String, Object>> source) {
        DataList1.clearData();
        for (Map<String, Object> row : source) {
            String iconUrl = IconHelper.resolveUrl("/res/icon/flag_" + row.get("Image") + ".png");
            DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE, iconUrl, row.get("Name"), row.get("Desc")),
                    null, true, false, null, null, null, false);
        }
    }

    public void btnReDataBind_Click(Object sender, EventArgs e) {
        autoBind();
    }
}
