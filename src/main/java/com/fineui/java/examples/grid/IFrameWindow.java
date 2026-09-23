package com.fineui.java.examples.grid;

import com.fineui.java.core.ActiveWindow;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.examples.code.ThirdPartyEditorPageBase;

/**
 * IFrame 窗体内的详情表单页（路由 {@code grid/iframe-window}）：被表格的「窗口列 / 双击行」弹出到 IFrame 窗体中，
 * 演示关闭窗体、保存后回发弹出窗体的关闭事件、保存后刷新父页、保存后关闭父页当前选项卡等窗体通信方式。
 */
@FineUIPage("grid/iframe-window")
public class IFrameWindow extends ThirdPartyEditorPageBase {

    public void Page_Load(Object sender, EventArgs e) {
    }

    /** 保存后隐藏本窗体并触发其关闭事件（回发到父页的窗体关闭处理器）。 */
    public void btnSaveClose_Click(Object sender, EventArgs e) {
        // 1. 这里放置保存窗体中数据的逻辑
        // 2. 关闭本窗体（触发窗体的关闭事件）
        ActiveWindow.hidePostBack();
    }

    /** 保存后隐藏本窗体并刷新父页。 */
    public void btnSaveHideRefresh_Click(Object sender, EventArgs e) {
        ActiveWindow.hideRefresh();
    }

    /** 保存后隐藏本窗体并关闭父页当前激活的选项卡（调用父页已定义的全局函数）。 */
    public void btnSaveCloseTab_Click(Object sender, EventArgs e) {
        ActiveWindow.hideCallParentFunction("removeActiveTab");
    }
}
