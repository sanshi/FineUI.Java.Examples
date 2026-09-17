package com.fineui.java.examples.iframe;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 子页保存刷新父表格（不关闭窗体，路由 {@code iframe/grid-iframe-reload}）：子页「更新父页面表格」
 * （{@code btnUpdateParentGrid_Click}）用 {@code invokeActiveWindowFunction("closeWindow1", 参数)} 调用
 * 父页脚本 {@code closeWindow1(参数)} -> {@code F.customEvent('CloseWindow1')} 回发父页
 * {@code Page_CustomEvent}，重新绑定表格并把标题改为「表格 - 回发参数：参数 - {ms}」，且<b>不关闭窗体</b>
 * （reload 而非 close）。
 */
@FineUIPage("iframe/grid-iframe-reload")
public class GridIFrameReload extends PageBase {

    Grid Grid1;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            autoBindGrid();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("CloseWindow1".equals(e.getEventName())) {
            autoBindGrid();
            Grid1.setTitle("表格 - 回发参数：" + e.getArgument());
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
