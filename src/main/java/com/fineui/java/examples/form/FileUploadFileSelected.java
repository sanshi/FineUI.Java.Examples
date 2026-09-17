package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Image;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.UploadPageBase;

/** 选择即上传（路由 {@code form/file-upload-file-selected}）：OnFileSelected——选文件后立即上传回显头像，无需提交表单。 */
@FineUIPage("form/file-upload-file-selected")
public class FileUploadFileSelected extends UploadPageBase {

    protected com.fineui.java.core.controls.FileUpload filePhoto;
    protected Image imgPhoto;
    protected TextBox tbxUserName;
    protected TextBox tbxEmail;
    protected Label labResult;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void filePhoto_FileSelected(Object sender, EventArgs e) {
        if (filePhoto.hasFile()) {
            // 校验：扩展名白名单 + 文件大小
            String error = validateUploadFile(filePhoto);
            if (error != null) {
                filePhoto.reset();
                showNotify(error);
                return;
            }

            // 保存并回显头像
            String savedName = saveUploadFile(filePhoto);
            imgPhoto.setImageUrl(getImageUrl(savedName));

            // 清空上传控件（否则提交表单时会再次上传！）
            filePhoto.reset();
        }
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        String url = imgPhoto.getImageUrl();
        if (url == null || url.endsWith("blank.png")) {
            filePhoto.markInvalid("请先上传个人头像！");
            showNotify("请先上传个人头像！");
            return;
        }

        labResult.setText("用户名：" + tbxUserName.getValue() + "<br/>"
                + "邮箱：" + tbxEmail.getValue() + "<br/>"
                + "<p>头像：<br /><img src=\"" + url + "\" /></p>");

        // 清空表单字段（清空上传控件，否则提交表单时会再次上传！）
        imgPhoto.setImageUrl("/res/images/blank.png");
        filePhoto.reset();
        tbxEmail.reset();
        tbxUserName.reset();
    }
}
