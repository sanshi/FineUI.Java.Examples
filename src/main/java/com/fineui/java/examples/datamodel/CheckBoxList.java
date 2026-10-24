package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.CheckBoxListForm;
import com.fineui.java.examples.datamodel.model.GenderType;

import java.util.List;

/**
 * 数据模型复选框与单选按钮列表（路由 {@code data-model/check-box-list}）：复选框、复选框列表及单选按钮列表
 * 经 {@code for} 绑定到 {@link CheckBoxListForm} 的集合/枚举属性（初始选中态随模型回显），
 * 提交回发后服务端把选择结果序列化为 JSON 展示。
 */
@FineUIPage("data-model/check-box-list")
public class CheckBoxList extends PageBase {
    @BindProperty
    private CheckBoxListForm form;

    protected DropDownList form_genderType_2;

    public CheckBoxListForm getForm() {
        return form;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Init(Object sender, EventArgs e) {
        if (!isPostBack()) {
            form = new CheckBoxListForm();
            form.setChecked(true);
            form.setCheckBoxList1(List.of("Value1", "Value3"));
            form.setRadioButtonList1("Value1");
            form.setGender(0);
            form.setGenderType(GenderType.WOMAN);
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            // 单选列表写入模型；下拉列表另设 name，直接从控件读取，允许两者选择不同。
            GenderType firstGender = form.getGenderType();
            String otherGender = form_genderType_2.getText();

            // 所有输入先转义，确保展示 JSON 时不把数据当 HTML。
            showNotify(new RawHtml(
                    "用户提交的数据：<br/><pre>%s</pre>"
                            + "模型性别：%s<br/>独立下拉列表 form_genderType_2：%s",
                    Json.encode(form), Json.encode(firstGender), Json.encode(otherGender)));
        }
    }
}
