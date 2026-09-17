package com.fineui.java.examples.gridurl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 网址数据源「树表格」示例共用的文件目录演示数据（文件夹/文件的层级结构）。
 * 字段：Id、ParentId（-1 为根）、Name、Type（「文件夹」为可展开节点，其余为文件叶子）、Size（文件夹为 null）、ModifyDate。
 *
 * <p>供树表格全量端点（一次返回整棵树）与延迟加载端点（先返回根、展开时按 parentId 返回子节点）复用。
 */
public final class FileTreeData {

    private FileTreeData() {
    }

    private record Node(int id, int parentId, String name, String type, Integer size, String modifyDate) {
    }

    // 目录树（层级由 parentId 串联；节点顺序即渲染次序）。
    private static final List<Node> NODES = List.of(
            new Node(50, -1, "basic", "文件夹", null, "2014-11-03 11:20:00"),
            new Node(54, 50, "captcha", "文件夹", null, "2014-08-17 20:22:00"),
            new Node(55, 54, "CaptchaController.java", "Java文件", 1, "2014-07-05 16:31:00"),
            new Node(56, 54, "CaptchaService.java", "Java文件", 2, "2014-07-05 16:31:00"),
            new Node(51, 50, "hello.html", "HTML文件", 1, "2014-07-05 16:31:00"),
            new Node(52, 50, "HelloController.java", "Java文件", 1, "2014-08-24 11:08:00"),
            new Node(53, 50, "HelloModel.java", "Java文件", 2, "2014-07-05 16:31:00"),
            new Node(200, -1, "common", "文件夹", null, "2014-08-17 20:22:00"),
            new Node(201, 200, "menu.xml", "XML文件", 44, "2014-11-27 17:10:00"),
            new Node(202, 200, "source.html", "HTML文件", 1, "2014-07-05 16:31:00"),
            new Node(203, 200, "SourceController.java", "Java文件", 3, "2014-08-30 16:31:00"),
            new Node(204, 200, "SourceModel.java", "Java文件", 2, "2014-07-05 16:31:00"),
            new Node(60, -1, "res", "文件夹", null, "2014-08-17 20:30:00"),
            new Node(61, 60, "css", "文件夹", null, "2014-11-16 17:54:00"),
            new Node(62, 61, "common.css", "CSS文件", 1, "2014-10-30 11:35:00"),
            new Node(63, 60, "images", "文件夹", null, "2014-10-09 15:45:00"),
            new Node(64, 63, "logo", "文件夹", null, "2014-04-26 11:29:00"),
            new Node(65, 64, "logo.png", "PNG文件", 5, "2013-09-11 12:13:00"),
            new Node(66, 64, "favicon.ico", "ICO文件", 18, "2013-09-11 12:13:00"),
            new Node(67, 64, "favicon.gif", "GIF文件", 1, "2013-09-11 12:13:00"),
            new Node(68, 63, "themes", "文件夹", null, "2014-07-10 11:52:00"),
            new Node(69, 68, "metro_blue.png", "PNG文件", 5, "2014-10-10 11:42:00"),
            new Node(70, 68, "metro_orange.png", "PNG文件", 5, "2014-10-10 11:43:00"),
            new Node(71, 68, "ui_darkness.png", "PNG文件", 5, "2014-10-10 11:41:00"),
            new Node(72, 68, "blank.png", "PNG文件", 4, "2013-09-11 12:12:00"),
            new Node(73, 68, "code.gif", "GIF文件", 1, "2013-09-11 12:12:00"),
            new Node(74, 68, "toolbox.png", "PNG文件", 39, "2013-09-11 12:13:00"),
            new Node(100, -1, "index.html", "HTML文件", 31, "2014-11-15 18:44:00"),
            new Node(101, -1, "Application.java", "Java文件", 13, "2014-10-27 18:44:00"),
            new Node(102, -1, "pom.xml", "XML文件", 12, "2014-10-12 20:57:00"),
            new Node(105, -1, "application.properties", "配置文件", 3, "2014-11-06 20:59:00")
    );

    // 延迟加载专用数据集（与全量树是两套数据：根节点名带「（延迟加载）」，展开 50/54 才返回其子节点）。
    private static final List<Node> LAZY_ROOTS = List.of(
            new Node(50, -1, "basic（延迟加载）", "文件夹", null, "2014-11-03 11:20:00"),
            new Node(100, -1, "index.html", "HTML文件", 31, "2014-11-15 18:44:00"),
            new Node(101, -1, "Application.java", "Java文件", 13, "2014-10-27 18:44:00"),
            new Node(102, -1, "pom.xml", "XML文件", 12, "2014-10-12 20:57:00"),
            new Node(105, -1, "application.properties", "配置文件", 3, "2014-11-06 20:59:00")
    );
    private static final List<Node> LAZY_BASIC_CHILDREN = List.of(
            new Node(54, 50, "captcha（延迟加载）", "文件夹", null, "2014-08-17 20:22:00"),
            new Node(51, 50, "hello.html", "HTML文件", 1, "2014-07-05 16:31:00"),
            new Node(52, 50, "HelloController.java", "Java文件", 1, "2014-08-24 11:08:00"),
            new Node(53, 50, "HelloModel.java", "Java文件", 2, "2014-07-05 16:31:00")
    );
    private static final List<Node> LAZY_CAPTCHA_CHILDREN = List.of(
            new Node(55, 54, "CaptchaController.java", "Java文件", 1, "2014-07-05 16:31:00"),
            new Node(56, 54, "CaptchaService.java", "Java文件", 2, "2014-07-05 16:31:00")
    );

    /** 整棵树（全量端点用）。 */
    public static List<Map<String, Object>> all() {
        return project(NODES.stream().toList());
    }

    /**
     * 大数据树（约 3500 节点，性能演示用）：以全量树为种子，再在若干文件夹节点下批量追加子节点直到 3500。
     * 结构确定可复现（id 从 10001 递增、父节点在现有文件夹间轮转），便于稳定渲染与断言。
     */
    public static List<Map<String, Object>> largeTree() {
        List<Map<String, Object>> list = all();
        // 可作父节点的现有文件夹 id（Type=文件夹）
        int[] folderParents = {50, 54, 60, 61, 63, 64, 68, 200};
        int id = 10001;
        int i = 0;
        while (list.size() < 3500) {
            int parent = folderParents[i % folderParents.length];
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", id);
            row.put("ParentId", parent);
            row.put("Name", "file-" + id + ".txt");
            row.put("Type", "TXT文件");
            row.put("Size", id % 50 + 1);
            row.put("ModifyDate", "2014-11-03 11:20:00");
            list.add(row);
            id++;
            i++;
        }
        return list;
    }

    /** 延迟加载根节点（lazyrowid 为空时返回）。 */
    public static List<Map<String, Object>> lazyRoots() {
        return project(LAZY_ROOTS);
    }

    /** 延迟加载子节点（展开某文件夹时按其 id 返回；50=basic、54=Captcha，其余无子节点）。 */
    public static List<Map<String, Object>> lazyChildrenOf(int parentId) {
        return switch (parentId) {
            case 50 -> project(LAZY_BASIC_CHILDREN);
            case 54 -> project(LAZY_CAPTCHA_CHILDREN);
            default -> new ArrayList<>();
        };
    }

    /** 根节点（延迟加载端点：lazyrowid 为空时返回）。 */
    public static List<Map<String, Object>> roots() {
        return childrenOf(-1);
    }

    /** 指定父节点的直接子节点（延迟加载端点：展开某文件夹时按其 id 返回）。 */
    public static List<Map<String, Object>> childrenOf(int parentId) {
        List<Node> matched = new ArrayList<>();
        for (Node n : NODES) {
            if (n.parentId() == parentId) {
                matched.add(n);
            }
        }
        return project(matched);
    }

    private static List<Map<String, Object>> project(List<Node> nodes) {
        List<Map<String, Object>> list = new ArrayList<>(nodes.size());
        for (Node n : nodes) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("Id", n.id());
            row.put("ParentId", n.parentId());
            row.put("Name", n.name());
            row.put("Type", n.type());
            row.put("Size", n.size());
            row.put("ModifyDate", n.modifyDate());
            list.add(row);
        }
        return list;
    }
}
