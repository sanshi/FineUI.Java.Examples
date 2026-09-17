package com.fineui.java.examples.home;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 回归：{@code meta[name=sourcefiles]} 三类声明值（template / class / res）的校验与读取。
 *
 * <p>正例只用 git 托管的文件（{@code templates/partial/partial-view.html}、{@code data/tree/website.xml}），
 * 不用 {@code static/res/**}——那是 sync-examples 生成的同步产物，干净检出时可能还不存在。
 */
class SourceFilesTest {

    // ===== template: =====

    @Test
    void templateAcceptsExistingPathAndReadsIt() {
        String path = SourceFiles.resolveTemplate("partial/partial-view.html");
        assertEquals("partial/partial-view.html", path);
        assertNotNull(SourceFiles.readTemplate(path));
    }

    @Test
    void templateRejectsMissingWrongSuffixAndUnsafePaths() {
        assertNull(SourceFiles.resolveTemplate("partial/no-such-view.html"), "文件不存在");
        assertNull(SourceFiles.resolveTemplate("partial/partial-view"), "缺 .html 后缀");
        assertNull(SourceFiles.resolveTemplate("/partial/partial-view.html"), "绝对路径");
        assertNull(SourceFiles.resolveTemplate("../application.properties"), "目录穿越");
        assertNull(SourceFiles.resolveTemplate("partial/../partial/partial-view.html"), "路径中段穿越");
        assertNull(SourceFiles.resolveTemplate(""), "空串");
        assertNull(SourceFiles.resolveTemplate(null), "null");
    }

    // ===== class: =====

    @Test
    void classAcceptsFullyQualifiedNameUnderExamplesPackage() {
        assertEquals(SourceFiles.class,
                SourceFiles.resolveClass("com.fineui.java.examples.home.SourceFiles"));
    }

    @Test
    void classRejectsOutsidePackageMissingAndUnsafeNames() {
        assertNull(SourceFiles.resolveClass("java.lang.String"), "包外的类");
        assertNull(SourceFiles.resolveClass("com.fineui.java.core.PageRegistry"), "库里的类也不接受");
        assertNull(SourceFiles.resolveClass("com.fineui.java.examples.home.NoSuchClass"), "类不存在");
        assertNull(SourceFiles.resolveClass("home/SourceFiles.java"), "旧的包路径写法已不支持");
        assertNull(SourceFiles.resolveClass("com.fineui.java.examples."), "以点结尾");
        assertNull(SourceFiles.resolveClass(null), "null");
    }

    // ===== res: =====

    @Test
    void resourceAcceptsExistingPathAndReadsIt() {
        String path = SourceFiles.resolveResource("data/tree/website.xml");
        assertEquals("data/tree/website.xml", path);
        String content = SourceFiles.readResource(path);
        assertNotNull(content);
        assertTrue(content.contains("<"), "读到的应是 XML 文本");
    }

    @Test
    void resourceRejectsMissingAndUnsafePaths() {
        assertNull(SourceFiles.resolveResource("data/tree/no-such-file.xml"), "文件不存在");
        assertNull(SourceFiles.resolveResource("/data/tree/website.xml"), "绝对路径");
        assertNull(SourceFiles.resolveResource("../../pom.xml"), "目录穿越");
        assertNull(SourceFiles.resolveResource("file:data/tree/website.xml"), "协议前缀");
        assertNull(SourceFiles.resolveResource("data\\tree\\website.xml"), "反斜杠");
        assertNull(SourceFiles.resolveResource(null), "null");
        // 只开放 static/ 与 data/ 两个根，别让"查看源代码"变成 classpath 任意资源读取入口
        assertNull(SourceFiles.resolveResource("application.properties"), "根目录下的配置文件");
        assertNull(SourceFiles.resolveResource("FineUIJava.lic.dev.config"), "授权文件");
        assertNull(SourceFiles.resolveResource("messages.properties"), "国际化资源包");
        assertNull(SourceFiles.resolveResource("templates/home/source.html"), "模板目录不在 res 白名单里");
        assertNull(SourceFiles.resolveResource("com/fineui/java/examples/home/Source.class"), "编译产物");
        assertNull(SourceFiles.resolveResource("static/../application.properties"), "白名单根后再穿越");
    }
}
