package com.fineui.java.examples.gridinput;

import com.fasterxml.jackson.databind.JsonNode;
import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;

/** 编辑框（购物车，路由 {@code grid-input/cart}）：行内数量输入实时算小计/总计，选中行参与合计，结算经自定义回发。 */
@FineUIPage("grid-input/cart")
public class Cart extends PageBase {

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(CartData.rows());
            Grid1.dataBind();
            Grid1.setSelectedRowIdArray(new String[] {"101", "102"});   // 默认选中前两件商品
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("GotoPay".equals(e.getEventName())) {
            JsonNode inputs = Json.parse(e.getArgument());   // [[id, 名称, 单价, 数量], ...]
            // 实际项目中应从服务端取商品单价（防止客户端篡改）
            double total = 0;
            int count = 0;
            StringBuilder sb = new StringBuilder();
            sb.append("<ol>");
            if (inputs != null && inputs.isArray()) {
                for (JsonNode item : inputs) {
                    if (item.isArray() && item.size() >= 4) {
                        double price = item.get(2).asDouble(0);
                        int number = item.get(3).asInt(0);
                        total += price * number;
                        count += number;   // 数量求和，而非选中行数
                        sb.append(String.format("<li>%s（单价：¥%.2f，数量：%d）</li>",
                                item.get(1).asText(), price, number));
                    }
                }
            }
            sb.append("</ol><hr/>");
            sb.append(String.format("<div style=\"text-align:center;\">共 %d 件商品，总计 ¥%.2f</div>", count, total));
            showNotifyRaw(sb.toString(), MessageBoxIcon.Information);
        }
    }
}
