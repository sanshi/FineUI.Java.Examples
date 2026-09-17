package com.fineui.java.examples.gridpaging;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;
import com.fineui.java.examples.code.PageBase;
import com.fineui.java.examples.code.StudentGridData2;

/**
 * 加载更多（表格内嵌链接，路由 {@code grid-paging/database-more-flow}）：「加载更多」链接由前端注入到表格
 * body 内部，点击走自定义事件 {@code MoreClick} 追加下一页数据；全部加载完毕后令客户端禁用该链接。
 * 当前已加载页码存进表格控件的自定义属性 data-index（随回发往返，避免占用服务端会话）。
 */
@FineUIPage("grid-paging/database-more-flow")
public class DatabaseMoreFlow extends PageBase {

    private static final int PAGESIZE = 5;

    Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Grid1.setDataSource(StudentGridData2.paged(0, PAGESIZE));
            Grid1.dataBind();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("MoreClick".equals(e.getEventName())) {
            doMoreClick();
        }
    }

    private void doMoreClick() {
        int dataIndex = currentDataIndex();
        dataIndex++;

        int pageCount = pageCount();
        if (dataIndex <= pageCount - 1) {
            Grid1.appendData(StudentGridData2.paged(dataIndex, PAGESIZE));   // 追加数据
            Grid1.setAttribute("data-index", String.valueOf(dataIndex));
        }

        if (dataIndex == pageCount - 1) {
            invokeClientFunction("disableMoreButton");
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
