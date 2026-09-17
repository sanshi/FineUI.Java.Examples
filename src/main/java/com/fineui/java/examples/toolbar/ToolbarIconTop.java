package com.fineui.java.examples.toolbar;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Image;
import com.fineui.java.examples.code.UploadPageBase;

/** 工具栏图标在上（路由 {@code toolbar/toolbar-icon-top}）：按钮图标在上文字在下（IconAlign=Top），含工具栏上传控件、选择即上传回显。 */
@FineUIPage("toolbar/toolbar-icon-top")
public class ToolbarIconTop extends UploadPageBase {

    protected com.fineui.java.core.controls.FileUpload filePhoto;
    protected Image imgPhoto;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void filePhoto_FileSelected(Object sender, EventArgs e) {
        if (filePhoto.hasFile()) {
            String error = validateUploadFile(filePhoto);
            if (error != null) {
                filePhoto.reset();
                showNotify(error);
                return;
            }

            String savedName = saveUploadFile(filePhoto);
            imgPhoto.setImageUrl(getImageUrl(savedName));

            filePhoto.reset();
        }
    }
}
