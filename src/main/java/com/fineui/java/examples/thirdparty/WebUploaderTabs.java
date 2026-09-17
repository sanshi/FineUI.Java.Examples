package com.fineui.java.examples.thirdparty;

import com.fineui.java.core.CustomEventArgs;
import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Grid;

/** 选项卡中的两个 WebUploader 示例。 */
@FineUIPage("third-party/web-uploader-tabs")
public class WebUploaderTabs extends WebUploaderPageBase {

    private static final String OWNER1 = "webuploader.webuploader_tabs.1";
    private static final String OWNER2 = "webuploader.webuploader_tabs.2";

    protected Grid Grid1;
    protected Grid Grid2;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            bindGrids();
        }
    }

    public void Page_CustomEvent(Object sender, CustomEventArgs e) {
        switch (e.getEventName()) {
            case "RebindGrid_Grid1" -> bindGrid(Grid1, OWNER1);
            case "RebindGrid_Grid2" -> bindGrid(Grid2, OWNER2);
            case "DeleteRow_Grid1" -> {
                deleteRow(OWNER1, e.getArgument());
                bindGrid(Grid1, OWNER1);
            }
            case "DeleteRow_Grid2" -> {
                deleteRow(OWNER2, e.getArgument());
                bindGrid(Grid2, OWNER2);
            }
            default -> {
            }
        }
    }

    private void bindGrids() {
        bindGrid(Grid1, OWNER1);
        bindGrid(Grid2, OWNER2);
    }

    private void bindGrid(Grid grid, String owner) {
        grid.setDataSource(WebUploaderStore.records(session(), owner));
        grid.dataBind();
    }
}
