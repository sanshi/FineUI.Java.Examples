package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 子页保存后顶层提示并回发（路由 {@code iframe/grid-iframe-alert}）：子页「更新父页面表格」服务端
 * {@code showAlertInTopHidePostBack("保存成功！", ..., "参数 - {ms}")}——提示框弹在顶层页面；点确定后
 * 隐藏窗体并回发（{@code HidePostBack} 走父页 {@code OnClose} 事件），{@code Window1_Close} 把表格标题改为
 * 「表格 - 回发参数：参数 - {ms}」（区别于 Script 版走 {@code customEvent}）。
 */
@FineUIPage("iframe/grid-iframe-alert")
public class GridIFrameAlert extends PageBase {

    Grid Grid1;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            autoBindGrid();
        }
    }

    /**
     * 交替绑定两张学生表：用控件自定义属性 {@code data-source-key}
     * 跨请求记录「当前绑定的表」，每次加载（含回发）都切换到另一张表。
     */
    private void autoBindGrid() {
        String sourceKey = dataSourceKey();
        if (sourceKey == null || sourceKey.isEmpty() || "table2".equals(sourceKey)) {
            bindGrid();
            sourceKey = "table1";
        } else {
            bindGrid2();
            sourceKey = "table2";
        }
        Grid1.setAttribute("data-source-key", sourceKey);
    }

    private String dataSourceKey() {
        String v = Grid1.getAttribute("data-source-key");
        return v == null ? "" : v;
    }

    private void bindGrid() {
        Grid1.setDataSource(StudentGridData.rows());
        Grid1.dataBind();
    }

    private void bindGrid2() {
        Grid1.setDataSource(StudentGridData2.rows());
        Grid1.dataBind();
    }

    public void Window1_Close(Object sender, EventArgs e) {
        autoBindGrid();
        String arg = e.getArgument();
        Grid1.setTitle("表格 - 回发参数：" + (arg == null || arg.isEmpty() ? "Window1_Close" : arg));
    }
}
