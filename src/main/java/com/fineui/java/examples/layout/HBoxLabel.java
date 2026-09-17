package com.fineui.java.examples.layout;

import com.fineui.java.core.EventArgs;
import com.fineui.java.core.FineUIPage;
import com.fineui.java.core.controls.Label;
import com.fineui.java.examples.code.PageBase;

/**
 * HBox（标签）演示页（路由 {@code layout/hbox-label}）：在 HBox 布局里对比内容面板与标签两种承载富文本的方式；
 * 第二个面板的标签内容在 {@code Page_Load} 首次加载时由服务端以可信 HTML 填充（标签声明 encode-text=false 不转义）。
 */
@FineUIPage("layout/hbox-label")
public class HBoxLabel extends PageBase {

    Label Label1;

    public void Page_Load(Object sender, EventArgs e) {
        if (!isPostBack()) {
            Label1.setText("<div class=\"zuckerberg\"><p><a ><b>马克·扎克伯格</b></a></p><p>马克·艾略特·扎克伯格（Mark Elliot Zuckerberg），美国社交网站Facebook的创办人，被人们冠以“第二盖茨”的美誉。哈佛大学计算机和心理学专业辍学生。据《福布斯》杂志保守估计，马克·扎克伯格拥有135亿美元身家，是2008年全球最年轻的巨富，也是历来全球最年轻的自行创业亿万富豪。</p><p>2014年2月10日，马克·扎克伯格及其华裔妻子普莉希拉·陈登上美国《慈善纪事报》2013年年度慈善排行榜榜首。</p></div>");
        }
    }
}
