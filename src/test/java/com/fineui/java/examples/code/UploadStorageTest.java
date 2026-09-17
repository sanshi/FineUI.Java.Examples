package com.fineui.java.examples.code;

import com.fineui.java.core.PageContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** 回归：上传文件的读回地址必须保留 Servlet context path。 */
class UploadStorageTest {

    @AfterEach
    void clearPageContext() {
        PageContext.clear();
    }

    @Test
    void downloadUrlsUseCurrentApplicationBaseUrl() {
        PageContext.begin(new Object(), false);
        assertEquals("/home/download?inline=1&file=photo+1.png", UploadStorage.imageUrl("photo 1.png"));
        assertEquals("/home/download?file=report+1.txt", UploadStorage.fileUrl("report 1.txt"));

        PageContext.current().setApplicationBaseUrl("/java/demo");
        assertEquals("/java/demo/home/download?inline=1&file=photo+1.png", UploadStorage.imageUrl("photo 1.png"));
        assertEquals("/java/demo/home/download?file=report+1.txt", UploadStorage.fileUrl("report 1.txt"));
    }
}
