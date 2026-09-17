package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.UploadPageBase;

/** 上传的 Ajax 遮罩提示（路由 {@code form/file-upload-loading}）：提交按钮显示遮罩加载提示，后台睡眠 1 秒以便观察遮罩。 */
@FineUIPage("form/file-upload-loading")
public class FileUploadLoading extends UploadPageBase {

    protected com.fineui.java.core.controls.FileUpload filePhoto;
    protected TextBox tbxUserName;
    protected Label labResult;
    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (filePhoto.hasFile()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

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
