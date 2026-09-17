package com.fineui.java.examples.formlayout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.CheckBox;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/** 表单布局——结账账单（路由 {@code form-layout/layout-check-out}）：分组面板 + 账单地址与联系人地址同步启停。 */
@FineUIPage("form-layout/layout-check-out")
public class LayoutCheckOut extends PageBase {

    protected CheckBox cbxSameAsContactAddress;
    protected TextBox tbxBillingAddress;
    protected TextBox tbxBillingProvince;
    protected TextBox tbxBillingCity;
    protected TextBox tbxBillingPostCode;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void cbxSameAsContactAddress_CheckedChanged(Object sender, EventArgs e) {
        boolean enabled = !cbxSameAsContactAddress.isChecked();
        tbxBillingAddress.setEnabled(enabled);
        tbxBillingProvince.setEnabled(enabled);
        tbxBillingCity.setEnabled(enabled);
        tbxBillingPostCode.setEnabled(enabled);
    }
}
