package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Image;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.UploadPageBase;

/** 隐藏上传控件 + 面板工具触发（路由 {@code form/file-upload-tool}）：点工具触发隐藏上传控件选文件，OnFileSelected 自动上传回显。 */
@FineUIPage("form/file-upload-tool")
public class FileUploadTool extends UploadPageBase {

    protected com.fineui.java.core.controls.FileUpload filePhoto;
    protected Image imgPhoto;
    protected TextBox tbxUserName;
    protected TextBox tbxEmail;
    protected Label labResult;

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

        imgPhoto.setImageUrl("/res/images/blank.png");
        filePhoto.reset();
        tbxEmail.reset();
        tbxUserName.reset();
    }
}
