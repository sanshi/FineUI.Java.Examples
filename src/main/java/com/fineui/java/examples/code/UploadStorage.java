package com.fineui.java.examples.code;

import com.fineui.java.core.IconHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 安全上传存储：把上传文件保存到静态资源之外的目录（浏览器无法直接 URL 访问），
 * 读回统一经公共下载入口 {@code /home/download} 输出（地址由 {@link #imageUrl} / {@link #fileUrl} 生成）。
 *
 * <p>校验：① 扩展名白名单（仅图片）② 大小上限（2MB）。防重名用时间戳 + 递增序号前缀。
 */
public final class UploadStorage {

    private UploadStorage() {
    }

    /** 允许上传的文件扩展名白名单（小写、不含点）。 */
    static final List<String> VALID_FILE_TYPES = List.of("jpg", "bmp", "gif", "jpeg", "png");

    /** 允许上传的最大字节数（示例限制为 2MB）。 */
    static final long MAX_FILE_SIZE = 2L * 1024 * 1024;

    // 上传文件保存目录：位于静态资源之外，静态资源处理够不到，浏览器无法直接通过 URL 访问，
    // 上传 .html 等文件也不会被当页面/脚本执行。读回统一通过公共下载页 /home/download。
    // ⚠️ 图片上传与 WebUploader 上传共用这一个目录，而 WebUploader 不设扩展名白名单、也不限 2MB。
    //    也就是说，本目录里文件的可达性取决于**最宽松的那个入口**：图片上传的白名单 + 2MB
    //    实际上可以从 WebUploader 那条路绕过（传个大图进来，再用图片地址内联显示）。
    //    示例站上无所谓；照抄到生产系统时，两条上传路径的校验强度要按同一个标准定。
    private static final Path UPLOAD_DIR = Path.of(System.getProperty("java.io.tmpdir"), "fineui-java-upload");

    // 同一毫秒内多文件（如多文件上传）防重名的递增序号。
    private static final AtomicLong SEQ = new AtomicLong();

    /** 从文件名中提取小写扩展名（不含点）。无扩展名返回空串。 */
    static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int dot = fileName.lastIndexOf('.');
        // 点在最后一位（如 "abc."）也视为无有效扩展名
        if (dot < 0 || dot == fileName.length() - 1) {
            return "";
        }
        // Locale.ROOT：默认 Locale 是土耳其语时 "GIF".toLowerCase() 会得到带点的 ı，
        // 白名单与 Content-Type 表都会因此匹配不上。
        return fileName.substring(dot + 1).toLowerCase(Locale.ROOT);
    }

    /**
     * 校验上传文件：① 大小上限 ② 扩展名白名单。通过返回 {@code null}，否则返回具体错误原因（可直接用于提示）。
     */
    public static String validate(String fileName, long size) {
        if (size <= 0) {
            return "文件为空！";
        }
        if (size > MAX_FILE_SIZE) {
            return "文件过大，最大允许 " + (MAX_FILE_SIZE / 1024 / 1024) + " MB！";
        }
        if (!VALID_FILE_TYPES.contains(getFileExtension(fileName))) {
            return "无效的文件类型！";
        }
        return null;
    }

    /** 生成防重名的保存文件名（时间戳 + 递增序号前缀 + 清理特殊字符）。 */
    static String buildUploadFileName(String originalName) {
        // 除了分隔符与空格，其余在 Windows 上非法的文件名字符（| < > " * ? 与控制字符）也要换掉：
        // 直接拿它们建文件会抛异常，不清理的话上传一个名为 a|b.txt 的文件就是 500。
        String name = (originalName == null ? "" : originalName)
                .replaceAll("[\\\\/:*?\"<>|\\p{Cntrl}]", "_")
                .replace(" ", "_");
        return System.currentTimeMillis() + "_" + SEQ.incrementAndGet() + "_" + name;
    }

    /**
     * 取文件名部分（剥离目录信息）。根路径（"/"、Windows 上的 "C:"）的 {@code getFileName()} 是 null，
     * 含非法路径字符（如 {@code a|b}）的 {@code Path.of} 会抛异常——两种都返回 {@code null}，
     * 由调用方判成非法输入返回 400，而不是让它们变成 500。
     */
    public static String safeFileName(String value) {
        try {
            Path name = Path.of(value).getFileName();
            return name == null ? null : name.toString();
        } catch (InvalidPathException e) {
            return null;
        }
    }

    /** 上传文件在服务端的物理路径（静态资源之外）。 */
    public static Path uploadFilePath(String fileName) {
        return UPLOAD_DIR.resolve(fileName);
    }

    /** 把输入流保存到上传目录（自动创建目录），返回保存后的文件名。 */
    public static String save(String originalName, InputStream in) throws IOException {
        String saved = buildUploadFileName(originalName);
        Files.createDirectories(UPLOAD_DIR);
        try (InputStream source = in) {
            Files.copy(source, uploadFilePath(saved), StandardCopyOption.REPLACE_EXISTING);
        }
        return saved;
    }

    // 允许内联显示的图片扩展名 -> Content-Type（与上传白名单 VALID_FILE_TYPES 的集合一致）。
    // 用硬编码表而不是 Files.probeContentType：后者读操作系统 mime 库/注册表，跨机器结果不确定；
    // 只有 5 项，一张表最省事也最可预测，五套示例的输出还能逐字对齐。
    private static final Map<String, String> IMAGE_CONTENT_TYPES = Map.of(
            "jpg", "image/jpeg",
            "jpeg", "image/jpeg",
            "png", "image/png",
            "gif", "image/gif",
            "bmp", "image/bmp");

    /** 取图片的 Content-Type；不在白名单内返回 {@code null}（调用方据此降级为附件下载）。 */
    public static String imageContentType(String fileName) {
        return IMAGE_CONTENT_TYPES.get(getFileExtension(fileName));
    }

    /** 上传图片的访问地址（指向公共下载入口 {@code /home/download}，带 {@code inline=1} 请求内联显示）。 */
    public static String imageUrl(String fileName) {
        return IconHelper.applicationUrl("/home/download?inline=1&file="
                + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    /**
     * 上传文件的访问地址（同一个公共下载入口，不带 inline，一律作为附件下载）。
     * 供服务端代码生成下载链接时使用；WebUploader 示例的下载链接是在页面脚本里按行拼的
     * （那时候文件名只在客户端有），拼法与本方法等价。
     */
    public static String fileUrl(String fileName) {
        return IconHelper.applicationUrl("/home/download?file="
                + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
}
