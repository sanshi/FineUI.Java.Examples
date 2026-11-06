package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

@FineUIPage("form/order-delivery")
public class OrderDelivery extends PageBase {
    protected DropDownList ddlDelivery;
    protected TextBox tbxAddress;
    protected Label labResult;

    public void ddlDelivery_SelectedIndexChanged(Object sender, EventArgs e) {
        boolean needsAddress = "courier".equals(ddlDelivery.getSelectedValue());
        tbxAddress.setRequired(needsAddress);
        tbxAddress.setShowRedStar(needsAddress);
        labResult.setHidden(true);
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        labResult.setHidden(false);
        if ("courier".equals(ddlDelivery.getSelectedValue())) {
            String address = tbxAddress.getValue();
            labResult.setText(address == null || address.isBlank()
                    ? "请填写收货地址"
                    : "订单已提交：快递配送，收货地址：" + address.strip());
            return;
        }

        labResult.setText("订单已提交：门店自提");
    }
}
