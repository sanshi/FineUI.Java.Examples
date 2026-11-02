package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Button;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;

/** 点击后追加备注输入框和按钮，并在后续回发读取新增字段。 */
@FineUIPage("form/form-dynamic-postback")
public class FormDynamicPostback extends PageBase {
    Panel Panel1;
    Label labResult;
    TextBox tbxRemark;
    Button btnConfirm;

    public void btnAdd_Click(Object sender, EventArgs e) {
        // 再次点击时已有控件会保留，避免重复创建相同 ID。
        if (tbxRemark != null) {
            labResult.setText("控件已存在，请输入备注后提交");
            return;
        }

        tbxRemark = new TextBox();
        tbxRemark.setId("tbxRemark");
        tbxRemark.setLabel("备注");
        tbxRemark.setEmptyText("请输入备注");
        tbxRemark.setRequired(true);
        tbxRemark.setShowRedStar(true);
        btnConfirm = new Button();
        btnConfirm.setId("btnConfirm");
        btnConfirm.setText("提交新增备注");
        btnConfirm.setOnClick("btnConfirm_Click");
        Panel1.addChild(tbxRemark, "items");
        Panel1.addChild(btnConfirm, "items");
        labResult.setText("已追加控件，请输入备注后提交");
    }

    public void btnConfirm_Click(Object sender, EventArgs e) {
        String remark = tbxRemark.getValue();
        labResult.setText(remark == null || remark.isBlank() ? "请填写备注" : "已提交：" + remark);
    }
}
