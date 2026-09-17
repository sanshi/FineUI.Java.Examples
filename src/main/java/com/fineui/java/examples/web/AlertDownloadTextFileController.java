package com.fineui.java.examples.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * 文本文件下载接口（消息框示例的辅助页）：
 * 直接返回一个 {@code text/plain} 附件（{@code Content-Disposition: attachment; filename=alert_download.txt}），
 * 供「点击确定按钮开始下载文件」类示例验证下载链路。
 */
@RestController
public class AlertDownloadTextFileController {

    // GET /Message/AlertDownloadTextFile（PascalCase 路径，测试与页面脚本按此访问；路由对大小写不敏感）
    @GetMapping("/Message/AlertDownloadTextFile")
    public ResponseEntity<byte[]> download() {
        byte[] bytes = "这是下载文件的内容！".getBytes(StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=alert_download.txt")
                .body(bytes);
    }
}
