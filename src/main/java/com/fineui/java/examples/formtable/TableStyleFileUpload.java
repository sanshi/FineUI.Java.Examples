package com.fineui.java.examples.formtable;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.UploadPageBase;

/** 表格样式（上传控件）（路由 {@code form-table/table-style-file-upload}）：表格化表单中嵌入文件上传控件，提交后保存并回显图片。 */
@FineUIPage("form-table/table-style-file-upload")
public class TableStyleFileUpload extends UploadPageBase {

    protected com.fineui.java.core.controls.FileUpload filePhoto;
    protected TextBox tbxUserName;
    protected Label labResult;
    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (filePhoto.hasFile()) {
            String originalName = filePhoto.getShortFileName();

            String error = validateUploadFile(filePhoto);
            if (error != null) {
                filePhoto.reset();
                showNotify(error);
                return;
            }

            String savedName = saveUploadFile(filePhoto);

            labResult.setText("<p>文件路径：" + htmlEncode(originalName) + "</p>"
                    + "<p>用户名：" + tbxUserName.getValue() + "</p>"
                    + "<p>头像：<br /><img src=\"" + getImageUrl(savedName) + "\" /></p>");

            SimpleForm1.reset();
        }
    }
}
