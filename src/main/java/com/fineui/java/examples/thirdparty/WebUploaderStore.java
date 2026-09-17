package com.fineui.java.examples.thirdparty;

import com.fineui.java.examples.code.UploadStorage;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * WebUploader 示例的会话数据与临时文件存储。
 *
 * <p>示例仅保存少量上传记录，用于演示重新绑定和删除，不应作为生产环境的大文件存储方案。
 *
 * <p>上传文件与头像/图片上传示例共用同一个目录（{@link UploadStorage}，位于静态资源之外，
 * 浏览器无法直接访问），读回一律经公共下载入口 {@code /home/download} 强制下载。
 */
public final class WebUploaderStore {

    public static final String FIXED_OWNER = "webuploader.webuploader_fixed";

    /**
     * owner 的固定前缀。五个 WebUploader 示例页的会话键都以它开头，用来挡住「实参写反」——
     * {@code deleteRow(owner, rowId)} 两个形参都是 String，写反编译器不报错，
     * 而未知 owner 在下面只会「建个空列表、什么都不删、不抛异常」，是最难查的那种静默失败。
     */
    public static final String OWNER_PREFIX = "webuploader.";

    /** 挡住写反的实参：行标识（UUID）不可能以 webuploader. 开头。 */
    private static void ensureOwner(String owner) {
        if (owner == null || !owner.startsWith(OWNER_PREFIX)) {
            throw new IllegalArgumentException(
                    "owner 必须是以 " + OWNER_PREFIX + " 开头的会话键（实参顺序是 owner 在前、行标识在后）：" + owner);
        }
    }

    private static final String ATTRIBUTE_PREFIX = "ThirdParty.WebUploader.";

    private WebUploaderStore() {
    }

    /** 取得某个上传组件的会话记录，不存在时创建空列表。 */
    @SuppressWarnings("unchecked")
    public static List<Map<String, Object>> records(HttpSession session, String owner) {
        ensureOwner(owner);
        Object value = session.getAttribute(attributeName(owner));
        if (value instanceof List<?> list) {
            return (List<Map<String, Object>>) list;
        }
        List<Map<String, Object>> records = new ArrayList<>();
        session.setAttribute(attributeName(owner), records);
        return records;
    }

    /** 取得固定章节上传页的四条初始记录。 */
    public static List<Map<String, Object>> fixedRecords(HttpSession session) {
        List<Map<String, Object>> records = records(session, FIXED_OWNER);
        if (records.isEmpty()) {
            records.add(fixedRecord("1_mulu", "目录"));
            records.add(fixedRecord("2_yinyan", "引言"));
            records.add(fixedRecord("3_zhengwen", "正文"));
            records.add(fixedRecord("4_cankao", "参考文献"));
        }
        return records;
    }

    /** 保存上传文件，并将记录写入当前会话。 */
    public static void save(HttpSession session, String owner, MultipartFile file) throws IOException {
        ensureOwner(owner);
        // 固定槽的会话键只能从后面那条分支写。owner 以它开头却不带「#行标识」时既不是固定槽上传、
        // 也不该当普通上传落进那个键——那会给固定页凭空多出一条记录（它只认自己播种的那几个槽）。
        // 挡在落盘之前：晚一步文件已经写到磁盘上了，拒了请求也会留下一个没人认领的文件。
        if (owner.startsWith(FIXED_OWNER) && !owner.startsWith(FIXED_OWNER + "#")) {
            throw new IllegalArgumentException("固定槽的 owner 必须形如 " + FIXED_OWNER + "#行标识：" + owner);
        }

        String originalName = shortFileName(file.getOriginalFilename());
        String savedName = UploadStorage.save(originalName, file.getInputStream());

        if (owner.startsWith(FIXED_OWNER + "#")) {
            String rowId = owner.substring((FIXED_OWNER + "#").length());
            Map<String, Object> record = find(fixedRecords(session), rowId);
            if (record == null) {
                throw new IllegalArgumentException("未找到固定上传记录：" + rowId);
            }
            record.put("name", originalName);
            record.put("type", fileType(originalName));
            record.put("savedName", savedName);
            record.put("size", file.getSize());
            record.put("status", "uploaded");
            return;
        }

        Map<String, Object> record = new LinkedHashMap<>();
        record.put("id", UUID.randomUUID().toString());
        record.put("name", originalName);
        record.put("type", fileType(originalName));
        record.put("savedName", savedName);
        record.put("size", file.getSize());
        record.put("status", "uploaded");
        records(session, owner).add(record);
    }

    /** 删除指定上传记录及其临时文件。固定章节页只重置该行，不删除章节。 */
    public static void delete(HttpSession session, String owner, String rowId) {
        ensureOwner(owner);
        List<Map<String, Object>> records = FIXED_OWNER.equals(owner) ? fixedRecords(session) : records(session, owner);
        Map<String, Object> record = find(records, rowId);
        if (record == null) {
            return;
        }

        deleteSavedFile(record.get("savedName"));
        if (FIXED_OWNER.equals(owner)) {
            record.put("name", "");
            record.put("type", "doc");
            record.put("savedName", "");
            record.put("size", null);
            record.put("status", "");
        } else {
            records.remove(record);
        }
    }

    /** 按已保存文件名读取上传文件，供固定章节页的下载链接与删除使用。 */
    public static Path filePath(String savedName) {
        if (savedName == null || savedName.isBlank() || savedName.contains("/") || savedName.contains("\\")) {
            return null;
        }
        return UploadStorage.uploadFilePath(savedName);
    }

    private static Map<String, Object> fixedRecord(String id, String sectionName) {
        Map<String, Object> record = new LinkedHashMap<>();
        record.put("id", id);
        record.put("sectionname", sectionName);
        record.put("name", "");
        record.put("type", "doc");
        record.put("savedName", "");
        record.put("size", "");
        record.put("status", "");
        return record;
    }

    private static String attributeName(String owner) {
        return ATTRIBUTE_PREFIX + owner;
    }

    private static Map<String, Object> find(List<Map<String, Object>> records, String rowId) {
        for (Map<String, Object> record : records) {
            if (rowId.equals(String.valueOf(record.get("id")))) {
                return record;
            }
        }
        return null;
    }

    private static void deleteSavedFile(Object savedName) {
        Path file = filePath(savedName == null ? null : String.valueOf(savedName));
        if (file != null) {
            try {
                Files.deleteIfExists(file);
            } catch (IOException ignored) {
                // 删除临时文件失败不影响示例中的会话记录更新。
            }
        }
    }

    private static String shortFileName(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            return "upload";
        }
        return Path.of(fileName).getFileName().toString();
    }

    private static String fileType(String fileName) {
        int dot = fileName.lastIndexOf('.');
        return dot < 0 || dot == fileName.length() - 1 ? "" : fileName.substring(dot + 1).toLowerCase();
    }
}
