package com.fineui.java.examples.dropdownlist;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.AjaxLoadingType;
import com.fineui.java.core.controls.DropDownList;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/** 演示下拉列表改变选中项时，页面默认值与控件属性如何控制回发加载提示。 */
@FineUIPage("drop-down-list/selected-index-changed-ajax-loading")
public class SelectedIndexChangedAjaxLoading extends PageBase {

    protected Label labResult;

    public void Page_Init(Object sender, EventArgs e) {
        getPageManager().ajaxLoadingType(AjaxLoadingType.Mask);
    }

    public void DropDownList_SelectedIndexChanged(Object sender, EventArgs e) {
        // 留出观察加载提示的时间；四个下拉列表都走同一个服务端事件。
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }

        DropDownList selectedList = (DropDownList) sender;
        labResult.setText(String.format("选中项：%s（值：%s）",
                selectedList.getText(), selectedList.getSelectedValue()));
    }
}
