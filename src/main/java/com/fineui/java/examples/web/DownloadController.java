package com.fineui.java.examples.web;

import com.fineui.java.examples.code.UploadStorage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

/**
 * 公共下载入口：图片上传示例与 WebUploader 示例共享。读取保存在静态资源之外（{@link UploadStorage}）
 * 的上传文件。
 *
 * <p>输出形态由<b>调用方</b>声明，而不是由文件扩展名推断：
 * 不带 inline（{@link UploadStorage#fileUrl}）→ 一律 {@code application/octet-stream} +
 * {@code Content-Disposition: attachment}，浏览器只下载不渲染；
 * 带 {@code inline=1}（{@link UploadStorage#imageUrl}）→ 仅当扩展名在图片白名单内才按
 * {@code image/xxx} 内联显示，白名单外（{@code .html} / {@code .svg} 等）降级为附件下载。
 *
 * <p>于是“能被内联渲染的集合”恒为那 5 种位图，与谁来请求、请求方怎么写参数无关。
 *
 * <p>安全：① 剥离目录信息防路径穿越；② 内联分支的图片白名单；③ {@code nosniff}。
 */
@RestController
public class DownloadController {

    // GET /home/download?file=xxx[&inline=1]
    @GetMapping("/home/download")
    public ResponseEntity<FileSystemResource> download(
            @RequestParam(required = false) String file,
            @RequestParam(required = false) String inline) {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // 安全①：只取文件名部分，剥离任何目录信息，防止路径穿越（如 ../../application.properties）。
        // 根路径与含非法路径字符的输入会让 getFileName() 返回 null / 让 Path.of 抛异常，一律当非法输入。
        String safeName = UploadStorage.safeFileName(file);
        if (safeName == null || !safeName.equals(file)) {
            return ResponseEntity.badRequest().build();
        }

        Path fullPath = UploadStorage.uploadFilePath(safeName);
        if (!Files.isRegularFile(fullPath)) {
            return ResponseEntity.notFound().build();
        }

        // 安全②：只有白名单内的图片、且调用方明确要求内联时，才按 image/xxx 输出。
        String imageContentType = isInlineRequested(inline) ? UploadStorage.imageContentType(safeName) : null;
        if (imageContentType != null) {
            // 不写 Content-Disposition，浏览器默认即内联
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(imageContentType))
                    // 禁止浏览器嗅探内容改写 Content-Type
                    .header("X-Content-Type-Options", "nosniff")
                    .body(new FileSystemResource(fullPath));
        }

        // 其余一律以附件下载：传上来的 .html / .svg 等不会被渲染，从根上杜绝存储型 XSS。
        // ContentDisposition 会用 UTF-8 写出 filename*，中文名不乱码。
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment().filename(safeName, StandardCharsets.UTF_8).build().toString())
                .header("X-Content-Type-Options", "nosniff")
                .body(new FileSystemResource(fullPath));
    }

    /**
     * 调用方是否要求内联显示。只认 {@code 1} / {@code true} 两个值，其余（含拼错的 no、off、空）
     * 一律当作没要求——未知输入落到附件下载这一侧，是两个方向里安全的那个。
     * 地址由 {@link UploadStorage#imageUrl} 生成、恒为 {@code inline=1}。
     */
    private static boolean isInlineRequested(String inline) {
        if (inline == null || inline.isEmpty()) {
            return false;
        }
        // Locale.ROOT：默认 Locale 是土耳其语时 "TRUE".toLowerCase() 会得到带点的 ı
        String value = inline.toLowerCase(Locale.ROOT);
        return "1".equals(value) || "true".equals(value);
    }
}
