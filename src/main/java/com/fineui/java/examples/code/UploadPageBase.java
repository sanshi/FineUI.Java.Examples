package com.fineui.java.examples.code;

import com.fineui.java.core.UploadedFile;
import com.fineui.java.core.controls.FileUpload;

import java.io.IOException;

/**
 * 含文件上传的示例页基类：在 {@link PageBase} 之上补「安全上传」便捷方法
 * （校验、保存到静态资源之外、生成公共下载地址），供上传类示例页复用。
 */
public abstract class UploadPageBase extends PageBase {

    /** 校验上传控件中的文件（单文件）：通过返回 {@code null}，否则返回错误原因。 */
    protected String validateUploadFile(FileUpload upload) {
        if (upload == null || !upload.hasFile()) {
            return "文件为空！";
        }
        UploadedFile f = upload.getPostedFile();
        return UploadStorage.validate(f.fileName(), f.size());
    }

    /** 校验一个上传文件（多文件场景逐个校验）：通过返回 {@code null}，否则返回错误原因。 */
    protected String validateUploadFile(UploadedFile file) {
        if (file == null || file.size() == 0) {
            return "文件为空！";
        }
        return UploadStorage.validate(file.fileName(), file.size());
    }

    /** 保存上传控件中的文件到静态资源之外的目录，返回保存后的文件名。 */
    protected String saveUploadFile(FileUpload upload) {
        try {
            return UploadStorage.save(upload.getShortFileName(), upload.getPostedFile().openStream());
        } catch (IOException e) {
            throw new RuntimeException("保存上传文件失败", e);
        }
    }

    /** 保存一个上传文件（多文件场景）到静态资源之外的目录，返回保存后的文件名。 */
    protected String saveUploadFile(UploadedFile file) {
        try {
            return UploadStorage.save(file.fileName(), file.openStream());
        } catch (IOException e) {
            throw new RuntimeException("保存上传文件失败", e);
        }
    }

    /** 上传图片的访问地址（指向公共图片下载入口）。 */
    protected String getImageUrl(String fileName) {
        return UploadStorage.imageUrl(fileName);
    }
}
