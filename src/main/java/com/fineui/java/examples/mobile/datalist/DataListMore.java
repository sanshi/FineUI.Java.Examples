package com.fineui.java.examples.mobile.datalist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.IconHelper;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.DataList;
import com.fineui.java.core.controls.HiddenField;
import com.fineui.java.core.controls.LinkButton;
import com.fineui.java.examples.code.DataSourceUtil;
import com.fineui.java.examples.mobile.MobilePageBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 移动端「加载更多」演示页（路由 {@code mobile/data-list/data-list-more}）：首屏显示一批国家，点「加载更多...」
 * 在列表末尾追加下一批（不重建整表）。当前页序用隐藏字段跨回发计数，加载 4 批后按钮置为不可用并显示「全部加载完毕」。
 */
@FineUIPage("mobile/data-list/data-list-more")
public class DataListMore extends MobilePageBase {

    DataList DataList1;
    HiddenField hfIndex;
    LinkButton btnMore;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            loadData();
        }
    }

    private void loadData() {
        // 首屏：清空后绑定第 0 批（6 项）
        DataList1.clearData();
        for (Map<String, Object> row : getDataByIndex(0)) {
            String iconUrl = IconHelper.resolveUrl("/res/icon/flag_" + row.get("Image") + ".png");
            DataList1.addItem(new RawHtml(DATALIST_ITEM_TEMPLATE, iconUrl, row.get("Name"), row.get("Desc")),
                    null, true, false, null, null, null, false);
        }
    }

    /** 按页序取一批数据；页序 &gt; 0 时给 Id/Name 加序号后缀，避免多批数据重复。 */
    private List<Map<String, Object>> getDataByIndex(int dataIndex) {
        List<Map<String, Object>> table = DataSourceUtil.getCountryTable();
        if (dataIndex > 0) {
            for (Map<String, Object> row : table) {
                row.put("Id", row.get("Id") + "_" + (dataIndex + 1));
                row.put("Name", row.get("Name") + " " + (dataIndex + 1));
            }
        }
        return table;
    }

    private void loadNextData(int dataIndex) {
        dataIndex++;
        if (dataIndex <= 4) {
            List<Map<String, Object>> batch = new ArrayList<>();
            for (Map<String, Object> row : getDataByIndex(dataIndex)) {
                String iconUrl = IconHelper.resolveUrl("/res/icon/flag_" + row.get("Image") + ".png");
                RawHtml text = new RawHtml(DATALIST_ITEM_TEMPLATE, iconUrl, row.get("Name"), row.get("Desc"));
                batch.add(DataList1.createItem(text, null, true, null, null, null, false));
            }
            // AppendData：在列表末尾追加一批（不重绑整表）
            DataList1.appendData(batch);
            hfIndex.setText(String.valueOf(dataIndex));
        }
        if (dataIndex == 4) {
            btnMore.setEnabled(false);
            btnMore.setText("全部加载完毕");
        }
    }

    public void btnMore_Click(Object sender, EventArgs e) {
        String dataIndexStr = hfIndex.getText();
        int dataIndex = (dataIndexStr == null || dataIndexStr.isEmpty()) ? 0 : Integer.parseInt(dataIndexStr);
        loadNextData(dataIndex);
    }
}
