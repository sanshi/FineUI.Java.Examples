package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.MessageBoxIcon;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.DropDownListForm;

import java.util.List;

/**
 * 数据模型下拉列表（路由 {@code data-model/drop-down-list}）：单选/多选下拉经 {@code for} 绑定到
 * {@link DropDownListForm}（初始选中值随模型回显，多选可继续追加值），提交回发后服务端读取绑定结果。
 */
@FineUIPage("data-model/drop-down-list")
public class DropDownList extends PageBase {
    @BindProperty
    private DropDownListForm form;

    public DropDownListForm getForm() {
        return form;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Get(Object sender, EventArgs e) {
        form = new DropDownListForm();
        form.setDropDownList1("Value5");
        form.setDropDownList2(List.of("Value1", "Value5"));
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            showNotify("用户提交的数据：单选=" + form.getDropDownList1() + "，多选=" + form.getDropDownList2(),
                    MessageBoxIcon.Success);
        }
    }
}
