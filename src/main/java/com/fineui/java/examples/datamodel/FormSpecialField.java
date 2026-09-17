package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.PowerForm;

/**
 * 数据模型特殊字段（路由 {@code data-model/form-special-field}）：TextBox 显示的是名称文本、但经
 * {@code for} 绑定到数值属性，绑定失败产生绑定错误；事件里用 {@code getModelState().remove(...)} 移除
 * 该绑定错误、单独处理该字段。
 */
@FineUIPage("data-model/form-special-field")
public class FormSpecialField extends PageBase {
    @BindProperty
    private PowerForm power;

    TextBox tbxViewPower;

    public PowerForm getPower() {
        return power;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Get(Object sender, EventArgs e) {
        power = new PowerForm();
        power.setViewPowerId(101);
        power.setViewPowerName("系统管理员");
    }

    public void btnSave_Click(Object sender, EventArgs e) {
        // 该字段由下方逻辑单独处理：先移除其绑定错误
        getModelState().remove("power.viewPowerId");

        if (!getModelState().isValid()) {
            return;
        }

        // 单独处理 ViewPowerId：从文本框文本反查
        String name = tbxViewPower.getValue();
        if (name == null || name.isBlank()) {
            power.setViewPowerId(null);
        } else if ("系统管理员".equals(name)) {
            power.setViewPowerId(101);
        } else {
            power.setViewPowerId(0);
        }
        showNotify("保存成功！ViewPowerId=" + power.getViewPowerId(), MessageBoxIcon.Success);
    }
}
