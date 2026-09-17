package com.fineui.java.examples.datamodel;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.RawHtml;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.datamodel.model.UICompareForm;

import java.time.LocalDate;

/**
 * 数据模型字段比较（路由 {@code data-model/ui-compare}）：表单经 {@code for} 绑定 {@link UICompareForm}，
 * 跨字段校验由 {@code @UICompare}（EndDate>StartDate、Number2≥Number1）与 {@code @Compare}（两次密码一致）
 * 驱动——客户端校验拦截 + 服务端自动绑定校验兜底，提交通过后展示缩进 JSON。
 */
@FineUIPage("data-model/ui-compare")
public class UICompare extends PageBase {
    @BindProperty
    private UICompareForm form;

    public UICompareForm getForm() {
        return form;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Get(Object sender, EventArgs e) {
        form = new UICompareForm();
        form.setStartDate(LocalDate.now());
        form.setNumber1(30);
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            // Json.encode 产出缩进 JSON 并做 HTML 转义，可安全放进 <pre> 展示
            showNotifyRaw(new RawHtml("用户提交的数据：<br/><pre>%s</pre>", Json.encode(form)));
        }
    }
}
