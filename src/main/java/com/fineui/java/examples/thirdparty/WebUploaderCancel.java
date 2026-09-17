package com.fineui.java.examples.thirdparty;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;

/** 可取消上传的 WebUploader 示例。 */
@FineUIPage("third-party/web-uploader-cancel")
public class WebUploaderCancel extends WebUploaderPageBase {

    private static final String OWNER = "webuploader.webuploader_cancel";

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
            deleteRow(OWNER, e.getArgument());
            bindGrid();
        } else if ("DeleteRows".equals(e.getEventName())) {
            deleteRows(OWNER, e.getArgument());
            bindGrid();
        }
    }

    private void bindGrid() {
        Grid1.setDataSource(WebUploaderStore.records(session(), OWNER));
        Grid1.dataBind();
    }
}
