package com.fineui.java.examples.form;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.UploadedFile;
import com.fineui.java.core.controls.Label;
import com.fineui.java.core.controls.SimpleForm;
import com.fineui.java.core.controls.TextBox;
import com.fineui.java.examples.code.UploadPageBase;

import java.util.ArrayList;
import java.util.List;

/** 多文件上传（路由 {@code form/file-upload-multiple}）：Multiple=true——一次选择多张图片并全部上传，结果区逐条列出。 */
@FineUIPage("form/file-upload-multiple")
public class FileUploadMultiple extends UploadPageBase {

    protected com.fineui.java.core.controls.FileUpload filePhotos;
    protected TextBox tbxUserName;
    protected Label labResult;
    protected SimpleForm SimpleForm1;

    public void Page_Load(Object sender, EventArgs e) {
    }

    public void btnSubmit_Click(Object sender, EventArgs e) {
        List<String> msg = new ArrayList<>();
        List<String> skipped = new ArrayList<>();

        for (UploadedFile filePhoto : filePhotos.getPostedFiles()) {
            String originalName = filePhoto.fileName();

            // 校验：扩展名白名单 + 文件大小；不通过则跳过该文件
            String error = validateUploadFile(filePhoto);
            if (error != null) {
                skipped.add(htmlEncode(originalName) + "（" + error + "）");
                continue;
            }

            // 保存并回显
            String savedName = saveUploadFile(filePhoto);

            msg.add("<div>路径：" + htmlEncode(originalName) + "</div>"
                    + "<div>照片：<br /><img src=\"" + getImageUrl(savedName) + "\" /></div>");
        }

        labResult.setText("<ol><li>" + String.join("</li><li>", msg) + "</li></ol>");

        // 有被跳过的文件时给出提示
        if (!skipped.isEmpty()) {
            showNotify("已跳过 " + skipped.size() + " 个文件：" + String.join("；", skipped));
        }

        // 清空表单字段（清空上传控件，否则提交表单时会再次上传！）
        SimpleForm1.reset();
    }
}
