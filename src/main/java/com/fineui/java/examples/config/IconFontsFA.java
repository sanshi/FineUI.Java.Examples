package com.fineui.java.examples.config;

import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.enums.IconFont;
import com.fineui.java.examples.code.PageBase;

/**
 * FontAwesome 图标（路由 {@code config/icon-fonts-fa}）：遍历 {@link IconFont} 枚举中
 * 非下划线开头的成员（FontAwesome 字体类 {@code f-icon-xxx}），生成图标列表供浏览。
 */
@FineUIPage("config/icon-fonts-fa")
public class IconFontsFA extends PageBase {

    /** FontAwesome 图标数量（非下划线开头、None 除外），模板显示用。 */
    public int getIconCount() {
        int count = 0;
        for (IconFont icon : IconFont.values()) {
            if (!icon.name().startsWith("_") && icon != IconFont.None) {
                count++;
            }
        }
        return count;
    }

    /** 图标列表 HTML（None + 全部 FontAwesome 项），模板经 {@code th:utext} 原样输出。 */
    public String getIconList() {
        StringBuilder sb = new StringBuilder("<ul class=\"icons\">");
        sb.append("<li class=\"f-state-default\"><i class=\"f-icon\"></i><div class=\"title\">None</div><div class=\"subtitle\">&nbsp;</div></li>");
        for (IconFont icon : IconFont.values()) {
            // 非下划线开头的是 FontAwesome 图标（下划线开头为自定义图标字体，见 IconFonts 页）
            if (icon.name().startsWith("_") || icon == IconFont.None) {
                continue;
            }
            String iconName = icon.getName();   // 例：search
            sb.append("<li class=\"f-state-default\"><i class=\"f-icon f-icon-").append(iconName)
                    .append("\"></i><div class=\"title\">").append(icon.name())
                    .append("</div><div class=\"subtitle\">").append(iconName).append("</div></li>");
        }
        sb.append("</ul>");
        return sb.toString();
    }
}
