package com.fineui.java.examples.config;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.Icon;
import com.fineui.java.examples.code.PageBase;

/**
 * 内置图标（路由 {@code config/icons}）：遍历 {@link Icon} 枚举（图片图标，文件位于
 * {@code /res/icon/}），生成图标列表供浏览；搜索框按关键字过滤。
 */
@FineUIPage("config/icons")
public class Icons extends PageBase {

    /** 图片图标数量（None 除外），模板显示用。 */
    public int getIconCount() {
        return Icon.values().length - 1;
    }

    /** 图标列表 HTML（None + 全部图片图标），模板经 {@code th:utext} 原样输出。 */
    public String getIconList() {
        StringBuilder sb = new StringBuilder("<ul class=\"icons\">");
        sb.append("<li class=\"f-state-default\"><img src=\"/res/images/empty.png\"/><div class=\"title\">None</div></li>");
        for (Icon icon : Icon.values()) {
            if (icon == Icon.None) {
                continue;
            }
            String fileName = icon.getName();   // 例：accept.png
            sb.append("<li class=\"f-state-default\"><img src=\"/res/icon/").append(fileName)
                    .append("\"/><div class=\"title\">").append(icon.name()).append("</div></li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
}
