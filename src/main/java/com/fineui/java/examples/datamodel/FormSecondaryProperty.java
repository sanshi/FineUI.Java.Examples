package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.StudentForm;

/**
 * 数据模型嵌套属性（路由 {@code data-model/form-secondary-property}）：表单字段经 {@code for} 绑定到
 * {@link StudentForm} 的<b>嵌套对象</b>（如 {@code score.xxx}），演示二级属性路径的绑定回显与提交回发。
 */
@FineUIPage("data-model/form-secondary-property")
public class FormSecondaryProperty extends PageBase {
    @BindProperty
    private StudentForm student;

    public StudentForm getStudent() {
        return student;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Get(Object sender, EventArgs e) {
        student = DataModelSamples.student();
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            student.setGroup(1);
            student.setEntranceYear(student.getEntranceDate() == null ? null : student.getEntranceDate().getYear());
            showNotify("用户提交的数据：" + student.getName() + "，数学=" + student.getScore().getMath(), MessageBoxIcon.Success);
        }
    }
}
