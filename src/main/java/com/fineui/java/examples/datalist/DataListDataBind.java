package com.fineui.java.examples.datalist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/** 普通页面从后台加载城市列表，重载后可继续追加具有独立编号的数据。 */
@FineUIPage("data-list/data-list-data-bind")
public class DataListDataBind extends PageBase {
    DataList DataList1;
    HiddenField hfBatch;
    Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadCities(false);
        }
    }

    private void loadCities(boolean reverse) {
        List<Map<String, String>> cities = new ArrayList<>(List.of(
                Map.of("id", "beijing", "name", "北京"),
                Map.of("id", "shanghai", "name", "上海"),
                Map.of("id", "guangzhou", "name", "广州")));
        if (reverse) {
            Collections.reverse(cities);
        }
        DataList1.clearData();
        for (Map<String, String> city : cities) {
            DataList1.addItem(city.get("name"), city.get("id"), true, false, null, null, null, false);
        }
    }

    public void btnReload_Click(Object sender, EventArgs e) {
        loadCities(true);
        hfBatch.setText("0");
        labResult.setText("数据数量：3");
    }

    public void btnAppend_Click(Object sender, EventArgs e) {
        // 批次在回发间保留，例如两次追加生成 shenzhen-1 和 shenzhen-2，避免重复项值。
        int batch = Integer.parseInt(hfBatch.getText()) + 1;
        DataList1.appendData(List.of(
                Map.of("text", "深圳 " + batch, "value", "shenzhen-" + batch, "enabled", true),
                Map.of("text", "杭州 " + batch, "value", "hangzhou-" + batch, "enabled", true)));
        hfBatch.setText(String.valueOf(batch));
        labResult.setText("数据数量：" + (3 + batch * 2));
    }
}
