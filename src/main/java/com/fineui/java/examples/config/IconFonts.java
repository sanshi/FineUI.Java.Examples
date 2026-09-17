package com.fineui.java.examples.config;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

/**
 * 自定义图标字体（路由 {@code config/icon-fonts}）：遍历 {@link IconFont} 枚举中以下划线
 * 开头的成员（图标字体类 {@code f-iconfont-xxx}），生成图标列表供浏览；搜索框按关键字过滤。
 */
@FineUIPage("config/icon-fonts")
public class IconFonts extends PageBase {

    /** 图标字体数量（下划线开头的成员），模板显示用。 */
    public int getIconCount() {
        int count = 0;
        for (IconFont icon : IconFont.values()) {
            if (icon.name().startsWith("_")) {
                count++;
            }
        }
        return count;
    }

    /** 图标列表 HTML（None + 全部图标字体项），模板经 {@code th:utext} 原样输出。 */
    public String getIconList() {
        StringBuilder sb = new StringBuilder("<ul class=\"icons\">");
        sb.append("<li class=\"f-state-default\"><i class=\"f-icon f-iconfont\"></i><div class=\"title\">None</div><div class=\"subtitle\">&nbsp;</div></li>");
        for (IconFont icon : IconFont.values()) {
            // 以下划线开头的是自定义图标字体（其余为 FontAwesome 图标，见 IconFontsFA 页）
            if (!icon.name().startsWith("_")) {
                continue;
            }
            String iconName = icon.getName();   // 例：f-iconfont-search
            String shortName = iconName.startsWith("f-iconfont-")
                    ? iconName.substring("f-iconfont-".length()) : iconName;
            sb.append("<li class=\"f-state-default\"><i class=\"f-icon f-iconfont ").append(iconName)
                    .append("\"></i><div class=\"title\">").append(icon.name())
                    .append("</div><div class=\"subtitle\">").append(shortName).append("</div></li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
}
