package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.CheckBoxListForm;
import com.fineui.java.examples.datamodel.model.GenderType;

import java.util.List;

/**
 * 数据模型复选框与单选按钮列表（路由 {@code data-model/check-box-list}）：两个复选框列表 + 单选按钮列表
 * 经 {@code for} 绑定到 {@link CheckBoxListForm} 的集合/枚举属性（初始选中态随模型回显），
 * 提交回发后服务端把选择结果序列化为 JSON 展示。
 */
@FineUIPage("data-model/check-box-list")
public class CheckBoxList extends PageBase {
    @BindProperty
    private CheckBoxListForm form;

    public CheckBoxListForm getForm() {
        return form;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Get(Object sender, EventArgs e) {
        form = new CheckBoxListForm();
        form.setChecked(true);
        form.setCheckBoxList1(List.of("Value1", "Value3"));
        form.setRadioButtonList1("Value1");
        form.setGender(0);
        form.setGenderType(GenderType.WOMAN);
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            showNotify("用户提交的数据：复选框=" + form.getChecked() + "，复选列表=" + form.getCheckBoxList1(),
                    MessageBoxIcon.Success);
        }
    }
}
