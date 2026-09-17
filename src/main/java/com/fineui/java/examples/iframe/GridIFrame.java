package com.fineui.java.examples.iframe;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.Panel;
import com.fineui.java.core.controls.TwinTriggerBox;
import com.fineui.java.core.controls.Window;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData;
import com.fineui.java.examples.code.StudentGridData2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 表格行命令打开 IFrame 窗体（路由 {@code iframe/grid-iframe}）：上方「搜索关键词
 * {@code ttbSearch} + 过滤条件下拉 {@code DropDownList1}」工具栏、表格行命令（编辑按钮，
 * {@code CssClass=editfield}）经 {@code WindowID} + {@code WindowIFrameUrlFormatString}/
 * {@code WindowTitleFormatString} 打开 IFrame 窗体（标题「编辑 - {Name}」），且 Window 的
 * {@code iframeload} 监听器触发 {@code onIFrameLoad}。表格数据每次加载经 {@code autoBindGrid()}
 * 在两张学生表间交替（用控件自定义属性 {@code data-source-key} 跨请求记录）。
 */
@FineUIPage("iframe/grid-iframe")
public class GridIFrame extends PageBase {

    Panel Panel7;
    Grid Grid1;
    TwinTriggerBox ttbSearch;
    Window Window1;

    public void Page_Load(Object sender, EventArgs e) {
        // 面板标题每次加载（含回发）都更新为当前时间
        Panel7.setTitle("表格 - 页面加载时间：" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        if (!isPostBack()) {
            autoBindGrid();
        }
    }

    public void DropDownList1_SelectedIndexChanged(Object sender, EventArgs e) {
        bindGrid();
    }

    public void Grid1_Sort(Object sender, EventArgs e) {
        showNotify(String.valueOf(e.getArgument()));
    }

    public void ttbSearch_Trigger1Click(Object sender, EventArgs e) {
        autoBindGrid();
        ttbSearch.setValue("");
        ttbSearch.setShowTrigger1(false);
    }

    public void ttbSearch_Trigger2Click(Object sender, EventArgs e) {
        autoBindGrid();
        ttbSearch.setShowTrigger1(true);
    }

    public void Window1_Close(Object sender, EventArgs e) {
        autoBindGrid();
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
}
