package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.binding.HiddenProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.core.RawHtml;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.StudentForm;

/**
 * 数据模型隐藏属性（路由 {@code data-model/form-hidden-property}）：演示 {@link com.fineui.java.binding.HiddenProperty}
 * 标记的页面状态字段跨回发保存（按钮切换 UserRoleId/UserRoleName），与 {@code for} 表单绑定并存——
 * 隐藏属性不渲染为控件、不出现在模板，回发重建时按声明类型恢复。
 */
@FineUIPage("data-model/form-hidden-property")
public class FormHiddenProperty extends PageBase {
    @BindProperty
    private StudentForm student;

    @HiddenProperty
    private int userRoleId;

    @HiddenProperty
    private String userRoleName;

    public StudentForm getStudent() {
        return student;
    }

    /**
     * 首屏加载表单初值与隐藏属性初值（回发不执行）：回发时表单值由客户端回传（框架兜底新建空实例承载），
     * 隐藏属性由 __FSTATE 恢复，两者都不需要也不应该在这里再赋一次。
     */
    public void Page_Get(Object sender, EventArgs e) {
        student = DataModelSamples.student();
        userRoleId = 101;
        userRoleName = "系统管理员";
    }

    public void btnModifyHiddenProperty_Click(Object sender, EventArgs e) {
        // 框架在进入本事件方法前已自动完成表单绑定与校验；本次只改隐藏属性、不碰表单，故清掉这些校验错误
        getModelState().clear();

        if ("系统管理员".equals(userRoleName)) {
            userRoleId = 202;
            userRoleName = "超级管理员";
        } else {
            userRoleId = 101;
            userRoleName = "系统管理员";
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            showNotifyRaw(new RawHtml("隐藏属性：<br/>UserRoleId：%d<br/>UserRoleName：%s", userRoleId, userRoleName), MessageBoxIcon.Success);
        }
    }
}
