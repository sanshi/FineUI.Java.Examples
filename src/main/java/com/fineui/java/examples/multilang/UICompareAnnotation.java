package com.fineui.java.examples.multilang;

import com.fineui.java.binding.BindProperty;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.Json;
import com.fineui.java.examples.code.MultilangPageBase;
import com.fineui.java.examples.multilang.model.UICompareModel;
import org.springframework.context.MessageSource;

import java.time.LocalDate;

/**
 * 多语言表单验证（数据注解，路由 {@code multi-lang/uicompare-annotation}）：字段走 {@code for} 绑定 +
 * {@code @Display(nameKey)}/{@code Compare}/{@code UICompare}（{@code #{资源键}} 消息），
 * 标签与校验消息均随语言切换；提交成功后用缩进 JSON 展示提交数据。
 */
@FineUIPage("multi-lang/uicompare-annotation")
public class UICompareAnnotation extends MultilangPageBase {

    public UICompareAnnotation(MessageSource messageSource) {
        super(messageSource);
    }

    @BindProperty
    private UICompareModel theModel;

    public UICompareModel getTheModel() {
        return theModel;
    }

    /** 首屏加载表单初值（回发不执行：那时表单值由客户端回传，框架兜底新建空实例承载）。 */
    public void Page_Get(Object sender, EventArgs e) {
        theModel = new UICompareModel();
        theModel.setStartDate(LocalDate.now());
        theModel.setNumber1(30);
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (getModelState().isValid()) {
            // 消息含 <br/><pre> 展示缩进 JSON，经可信 HTML 通道输出
            showNotifyRaw(_R("multilang.uiCompareAnnotation.successMessage", Json.encode(theModel)));
        }
    }
}
