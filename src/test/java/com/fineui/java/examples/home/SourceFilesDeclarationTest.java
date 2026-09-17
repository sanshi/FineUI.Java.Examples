package com.fineui.java.examples.home;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * 回归：全仓示例模板里 {@code meta[name=sourcefiles]} 的每一项都必须能解析出源文件。
 *
 * <p>声明写错（前缀拼错、路径/类名写错、文件被删或改名）时，运行期只是<b>静默少一个选项卡</b>，
 * 页面看不出异常——本用例把这种静默失败提前变成构建失败。
 */
class SourceFilesDeclarationTest {

    private static final Pattern META =
            Pattern.compile("<meta\\s+name=\"sourcefiles\"\\s+content=\"([^\"]*)\"");

    /**
     * {@code static/res/} 整棵树是 {@code sync-examples} 从 Pro 示例同步生成的产物、不进 git；干净检出后
     * 还没同步过时它整个不存在，此时跳过这类条目（其余条目照常校验），避免把"还没同步"报成"声明写错"。
     */
    private static final boolean SYNCED_RES_PRESENT =
            Files.isDirectory(Path.of("src/main/resources/static/res"));

    @Test
    void everyDeclarationResolvesToARealSourceFile() throws IOException {
        Path templates = Path.of("src/main/resources/templates");
        assumeTrue(Files.isDirectory(templates), "非模块根目录运行，跳过");

        List<String> problems = new ArrayList<>();
        int declarations = 0;

        try (Stream<Path> files = Files.walk(templates)) {
            for (Path file : files.filter(p -> p.toString().endsWith(".html")).toList()) {
                String text = Files.readString(file, StandardCharsets.UTF_8);
                Matcher m = META.matcher(text);
                while (m.find()) {
                    String where = templates.relativize(file).toString().replace('\\', '/');
                    for (String item : m.group(1).split(";")) {
                        String value = item.trim();
                        if (value.isEmpty()) {
                            continue;
                        }
                        declarations++;
                        String problem = check(value);
                        if (problem != null) {
                            problems.add(where + " → " + value + "（" + problem + "）");
                        }
                    }
                }
            }
        }

        assertTrue(declarations > 0, "一条 sourcefiles 声明都没扫到，正则或目录不对");
        assertTrue(problems.isEmpty(), "以下 sourcefiles 声明解析不出源文件：\n  " + String.join("\n  ", problems));
    }

    /** 逐项校验，合法返回 {@code null}，否则返回问题描述。 */
    private static String check(String value) {
        int colon = value.indexOf(':');
        if (colon <= 0) {
            return "缺少 route:/template:/class:/res: 前缀";
        }
        String prefix = value.substring(0, colon);
        String path = value.substring(colon + 1);
        return switch (prefix) {
            // route: 的值同时是路由与模板路径（路由 == 视图名 == 模板路径），故按模板存在性校验；
            // 是否真的注册成页面由 PageRegistry 在启动时保证，这里不起容器。
            case "route" -> SourceFiles.resolveTemplate(path + ".html") == null ? "找不到对应模板" : null;
            case "template" -> SourceFiles.resolveTemplate(path) == null ? "找不到模板" : null;
            case "class" -> SourceFiles.resolveClass(path) == null ? "找不到类" : null;
            case "res" -> (!SYNCED_RES_PRESENT && path.startsWith("static/res/")) ? null
                    : SourceFiles.resolveResource(path) == null ? "找不到资源" : null;
            default -> "未知前缀 " + prefix;
        };
    }

}
