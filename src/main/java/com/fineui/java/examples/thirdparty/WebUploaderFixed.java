package com.fineui.java.examples.thirdparty;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;

/** 固定章节的 WebUploader 示例。 */
@FineUIPage("third-party/web-uploader-fixed")
public class WebUploaderFixed extends WebUploaderPageBase {

    protected Grid Grid1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrid();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        if ("RebindGrid".equals(e.getEventName())) {
            bindGrid();
        } else if ("DeleteRow".equals(e.getEventName())) {
            deleteRow(WebUploaderStore.FIXED_OWNER, e.getArgument());
            bindGrid();
        } else if ("DeleteRows".equals(e.getEventName())) {
            deleteRows(WebUploaderStore.FIXED_OWNER, e.getArgument());
            bindGrid();
        }
    }

    private void bindGrid() {
        Grid1.setDataSource(WebUploaderStore.fixedRecords(session()));
        Grid1.dataBind();
    }
}
