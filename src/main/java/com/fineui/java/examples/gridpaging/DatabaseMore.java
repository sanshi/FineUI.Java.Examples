package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.core.controls.LinkButton;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 加载更多（服务端按钮，路由 {@code grid-paging/database-more}）：表格不显示分页工具栏，底部「加载更多...」
 * 链接按钮每次回发追加下一页数据（行数累加而非替换）。当前已加载页码存进表格控件的自定义属性 data-index
 * （随回发往返，避免占用服务端会话）。
 */
@FineUIPage("grid-paging/database-more")
public class DatabaseMore extends PageBase {

    private static final int PAGESIZE = 5;

    Grid Grid1;
    LinkButton btnMore;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.paged(0, PAGESIZE));
            Grid1.dataBind();
        }
    }

    public void btnMore_Click(Object sender, EventArgs e) {
        int dataIndex = currentDataIndex();
        dataIndex++;

        int pageCount = pageCount();
        if (dataIndex <= pageCount - 1) {
            Grid1.appendData(StudentGridData2.paged(dataIndex, PAGESIZE));   // 追加数据
            Grid1.setAttribute("data-index", String.valueOf(dataIndex));
        }

        if (dataIndex == pageCount - 1) {
            btnMore.setEnabled(false);
            btnMore.setText("全部加载完毕");
        }
    }

    private int pageCount() {
        return (int) Math.ceil((double) StudentGridData2.count() / PAGESIZE);
    }

    /** 从表格控件的自定义属性 data-index 读当前已加载页码（随回发往返带回）。 */
    private int currentDataIndex() {
        String v = Grid1.getAttribute("data-index");
        return v == null ? 0 : Integer.parseInt(v);
    }
}
