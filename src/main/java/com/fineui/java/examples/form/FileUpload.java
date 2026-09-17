package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.UploadPageBase;

/** 文件上传基础（路由 {@code form/file-upload}）：选择照片、提交表单，服务端保存并回显图片。 */
@FineUIPage("form/file-upload")
public class FileUpload extends UploadPageBase {

    // 字段类型用全限定名，避免与本示例页类名 FileUpload 冲突。
    protected com.fineui.java.core.controls.FileUpload filePhoto;
    protected TextBox tbxUserName;
    protected Label labResult;
    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        if (filePhoto.hasFile()) {
            String originalName = filePhoto.getShortFileName();

            // 校验：扩展名白名单 + 文件大小
            String error = validateUploadFile(filePhoto);
            if (error != null) {
                // 清空上传控件（否则提交表单时会再次上传！）
                filePhoto.reset();
                showNotify(error);
                return;
            }

            // 保存到静态资源之外的目录，图片地址指向公共图片下载入口
            String savedName = saveUploadFile(filePhoto);

            labResult.setText("<p>文件路径：" + htmlEncode(originalName) + "</p>"
                    + "<p>用户名：" + tbxUserName.getValue() + "</p>"
                    + "<p>头像：<br /><img src=\"" + getImageUrl(savedName) + "\" /></p>");

            // 清空表单字段（清空上传控件，否则提交表单时会再次上传！）
            SimpleForm1.reset();
        }
    }
}
