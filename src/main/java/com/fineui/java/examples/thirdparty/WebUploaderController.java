package com.fineui.java.examples.thirdparty;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * WebUploader 示例的上传接口。
 *
 * <p>handler 保留成一个固定值 Process：五个示例页的 SERVER_URL 都带着它，
 * 下载搬去公共入口后它不再有分发作用，但去掉要改一批页面且没有实际收益。
 *
 * <p>上传文件保存在静态资源之外（{@link com.fineui.java.examples.code.UploadStorage}），浏览器无法直接访问；
 * 读回走公共下载入口 {@code /home/download}（下载链接在页面脚本里按行拼，不带 inline），强制
 * application/octet-stream + Content-Disposition: attachment，所以传上来的 .html 等只会被下载，
 * 绝不会被当页面/脚本执行。
 *
 * <p>与头像/图片上传示例共用同一个上传目录，也共用同一个下载入口——区别只在调用方要不要 inline：
 * 图片示例的 {@code UploadStorage.imageUrl} 带 inline=1（按 image/xxx 内联显示），
 * WebUploader 的下载链接不带（任意文件、恒下载）。
 */
@RestController
public class WebUploaderController {

    @PostMapping("/third-party/upload")
    public ResponseEntity<String> process(
            @RequestParam String handler,
            @RequestParam(required = false) String owner,
            @RequestParam(required = false) MultipartFile file,
            HttpSession session) {
        if (!"Process".equals(handler)) {
            return ResponseEntity.notFound().build();
        }
        if (owner == null || owner.isBlank() || file == null || file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No file");
        }

        try {
            WebUploaderStore.save(session, owner, file);
            return ResponseEntity.ok("Success");
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No file");
        }
    }
}
