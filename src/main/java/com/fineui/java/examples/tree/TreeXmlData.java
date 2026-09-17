package com.fineui.java.examples.tree;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 树示例数据源工具：从 classpath 的 {@code data/tree/*.xml} 读取并解析为 XML 文档，供树控件数据绑定示例使用。
 */
final class TreeXmlData {

    private TreeXmlData() {
    }

    /** 解析 classpath 上的 XML 文件为文档对象（如 {@code data/tree/website.xml}）。 */
    static Document parse(String classpathPath) {
        try (InputStream in = TreeXmlData.class.getClassLoader().getResourceAsStream(classpathPath)) {
            if (in == null) {
                throw new IllegalStateException("资源不存在: " + classpathPath);
            }
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return factory.newDocumentBuilder().parse(in);
        } catch (Exception e) {
            throw new IllegalStateException("解析树 XML 数据失败: " + classpathPath, e);
        }
    }
}
